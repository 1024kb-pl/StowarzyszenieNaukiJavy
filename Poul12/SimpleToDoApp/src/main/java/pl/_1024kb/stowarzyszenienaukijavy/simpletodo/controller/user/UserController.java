package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.UserNotFoundException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.TaskServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.UserServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.EntityCreator;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.MailSender;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.PBKDF2Hash;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController
{
    private UserServiceImpl userService;
    private TaskServiceImpl taskService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userService, TaskServiceImpl taskService)
    {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/register")
    public String redirectToRegister(Model model)
    {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user,  Model model)
    {
        //request.setCharacterEncoding("UTF-8");

        //User user = new EntityCreator().createUser(model);

        System.out.println(user);

        String message = "Pomyślnie zarejestrowano nowego użytkownika";

        try
        {
            userService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/login")
    public String redirectToLogin()
    {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model, @RequestParam String username, @RequestParam String password)
    {
        String message = "Nie udało się zalogować :(";

        try {
            if (userService.loginVerification(username, password)) {
                message = "Witaj " + username + " :)";
                session.setAttribute("username", username);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model, @SessionAttribute String username)
    {
        String message = "Nie udało się wylogować :/";

        if (session != null)
        {
            session.invalidate();
            message = "Wylogowano ;)";
            logger.info("Pomyślnie wylogowano użytkownika {}", username);
            MDC.remove("user");
        }
        else {
            logger.error("Błąd podczas wylogowywania się uzytkownika {}", username);
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/editUser")
    public String redirectToEdit(Model model, HttpServletResponse response, @SessionAttribute String username) throws IOException
    {
        User user = userService.getUserByUsername(username).orElseThrow(this::newRunTimeException);

        model.addAttribute("user", user);

        if(username != null)
        {
            return "edituser";
        }else
        {
            response.sendError(403);
        }

        return "index";
    }

    @PostMapping("/editUser")
    public String editUser(HttpServletRequest request, HttpSession session,  Model model, @SessionAttribute(name = "username") String sessionUsername, @RequestParam String username) throws UnsupportedEncodingException
    {
        request.setCharacterEncoding("UTF-8");

        if(!sessionUsername.equals(username))
        {
            //request.getSession(false).setAttribute("username", username);
            session.setAttribute("username", username);
        }

        User user = new EntityCreator().updateUser(model);

        String message = "Pomyślnie zmieniono dane użytownika";
        try
        {
            userService.editUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/deleteUser")
    public String redirectToDeleteUser()
    {
        return "deleteuser";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(HttpSession session, Model model, @SessionAttribute String username, @RequestParam String confirmedPassword)
    {
        String userPassword = userService.getUserByUsername(username)
                                .orElseThrow(this::newRunTimeException)
                                .getPassword();

        confirmedPassword = PBKDF2Hash.encode(confirmedPassword);

        String message = "Pomyślnie usunięto użytkownika";
        try
        {
            if(userPassword.equals(confirmedPassword))
            {
                taskService.deleteAllTasks(username);
                userService.removeUser(username);
                session.invalidate();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/reset")
    public String redirectToResetPass()
    {
        return "resetpass";
    }

    @PostMapping
    public String resetPassword(Model model, @RequestParam String email)
    {
        User user = userService.getUserByEmail(email);

        String message = "Password reset, please check Your email :)";
        String newPass = user.getPassword().substring(0, 10);
        try
        {
            MailSender.sendEmail(email, newPass);
        } catch (MessagingException e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        try
        {
            user.setPassword(newPass);
            user.setRepeatedPassword(newPass);
            userService.editUser(user);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        model.addAttribute("message", message);

        return "message";
    }

    /*@GetMapping("/message")
    public String message()
    {
        return "message";
    }*/

    private UserNotFoundException newRunTimeException()
    {
        return new UserNotFoundException("Not found any desired user");
    }
}
