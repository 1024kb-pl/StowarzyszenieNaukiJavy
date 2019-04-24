package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.UserNotFoundException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.TaskServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.UserServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.EntityCreator;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.MailSender;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController
{
    private UserServiceImpl userService; //= new UserServiceImpl();//UserServiceImpl.getInstance();
    private TaskServiceImpl taskService;// = TaskServiceImpl.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userService, TaskServiceImpl taskService)
    {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/register")
    public String redirectToRegister()
    {
        return "register";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request) throws IOException
    {
        request.setCharacterEncoding("UTF-8");

        User user = new EntityCreator().createUser(request);

        String message = "Pomyślnie zarejestrowano nowego użytkownika";
        System.out.println(user);
        try
        {
            userService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        return "message";
    }

    @GetMapping("/login")
    public String redirectToLogin()
    {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        String message = "Nie udało się zalogować :(";

        try {
            if (userService.loginVerification(username, password)) {
                message = "Witaj " + username + " :)";
                request.getSession(true).setAttribute("username", username);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        return "message";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, @SessionAttribute String username)
    {
        String message = "Nie udało się wylogować :/";

        if (request.getSession(false) != null)
        {
            request.getSession().invalidate();
            message = "Wylogowano ;)";
            logger.info("Pomyślnie wylogowano użytkownika {}", username);
            MDC.remove("user");
        }
        else {
            logger.error("Błąd podczas wylogowywania się uzytkownika {}", username);
        }

        request.setAttribute("message", message);

        return "message";
    }

    @GetMapping("/editUser")
    public String redirectToEdit(HttpServletRequest request, HttpServletResponse response, @SessionAttribute String username) throws IOException
    {
        User user = userService.getUserByUsername(username).orElseThrow(this::newRunTimeException);

        request.setAttribute("userId", user.getUserId());
        request.setAttribute("username", user.getUsername());
        request.setAttribute("email", user.getEmail());

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
    public String editUser(HttpServletRequest request, @SessionAttribute(name = "username") String sessionUsername, @RequestParam String username) throws UnsupportedEncodingException
    {
        request.setCharacterEncoding("UTF-8");

        if(!sessionUsername.equals(username))
        {
            request.getSession(false).setAttribute("username", username);
        }

        User user = new EntityCreator().updateUser(request);
        System.out.println("username user: " + user);
        String message = "Pomyślnie zmieniono dane użytownika";
        try
        {
            userService.editUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        return "message";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request, @SessionAttribute String username)
    {
        String message = "Pomyślnie usunięto użytkownika";
        try
        {
            taskService.deleteAllTasks(username);
            userService.removeUser(username);
            request.getSession(false).invalidate();

        }catch (Exception e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        request.setAttribute("message", message);

        return "message";
    }

    @GetMapping("/reset")
    public String redirectToResetPass()
    {
        return "resetpass";
    }

    @PostMapping
    public String resetPassword(HttpServletRequest request, @RequestParam String email)
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

        request.setAttribute("message", message);

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
