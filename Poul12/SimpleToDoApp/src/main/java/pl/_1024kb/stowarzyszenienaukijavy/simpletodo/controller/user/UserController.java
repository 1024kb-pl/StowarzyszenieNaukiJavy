package pl._1024kb.stowarzyszenienaukijavy.simpletodo.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.NotFoundDesiredDataRuntimeException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.security.CustomUserDetailsService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.TaskServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.UserServiceImpl;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.MailSender;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserController
{
    private UserServiceImpl userService;
    private TaskServiceImpl taskService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepo;
    private MailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userService, TaskServiceImpl taskService, PasswordEncoder passwordEncoder, MailSender mailSender)
    {
        this.userService = userService;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/register")
    public String redirectToRegister(Model model)
    {
        model.addAttribute(new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, BindingResult result, Model model)
    {
        System.out.println(user);

        String message = "The new user was created";

        if(result.hasErrors())
        {
            return "register";
        }else
        {
            try
            {
                userService.createUser(user);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                message = e.getMessage();
            }
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/login")
    public String redirectToLogin()
    {
        return "login";
    }

    @PostMapping("/sessionLogin")
    public String login(HttpSession session, @RequestParam String username)
    {
        UserDetails userDetails = new CustomUserDetailsService(userRepo).loadUserByUsername(username);

        System.out.println("credentials user: " + userDetails);

        if(userDetails != null)
        {
            session.setAttribute("username", userDetails.getUsername());
            logger.debug("Zalogowano pomyślnie użytkownika {}", userDetails.getUsername());
        }else
        {
            logger.debug("Logowanie na użytkownika {} nie udało się", username);
        }

        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model, @SessionAttribute String username)
    {
        String message = "Logout invalid";

        if (session != null)
        {
            session.invalidate();
            message = "Logout successfully";
            logger.debug("Pomyślnie wylogowano użytkownika {}", username);
        }
        else {
            logger.debug("Błąd podczas wylogowywania się uzytkownika {}", username);
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/editUser")
    public String redirectToEdit(Model model, HttpServletResponse response, @SessionAttribute String username) throws IOException
    {
        User user = userService.getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);

        model.addAttribute("user", user);

        if(username != null)
        {
            return "edit_user";
        }else
        {
            response.sendError(403);
        }

        return "index";
    }

    @PostMapping("/editUser")
    public String editUser(Model model, HttpSession session, @Valid @ModelAttribute User user, BindingResult result, @SessionAttribute(name = "username") String sessionUsername)
    {
        String message = "User data was successfully changed";

        if(result.hasErrors())
        {
            return "edit_user";
        }else
        {
            if(!sessionUsername.equals(user.getUsername()))
            {
                session.setAttribute("username", user.getUsername());
            }
            try
            {
                userService.editUser(user);
            } catch (Exception e) {
                e.printStackTrace();
                message = e.getMessage();
            }
        }

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/deleteUser")
    public String redirectToDeleteUser()
    {
        return "delete_user";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(HttpSession session, Model model, @SessionAttribute String username, @RequestParam String confirmedPassword)
    {
        String userPassword = userService.getUserByUsername(username)
                                .orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException)
                                .getPassword();

        boolean isMatch = passwordEncoder.matches(confirmedPassword, userPassword);
        String message = "User was removed";

        try
        {
            if(isMatch)
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
        return "reset_pass";
    }

    @PostMapping("/reset")
    public String resetPassword(Model model, @RequestParam String email)
    {
        User user = userService.getUserByEmail(email);

        String message = "Password reset, please check Your email :)";
        String newPass = user.getPassword().substring(24, 36);

        try
        {
            mailSender.sendEmail(email, newPass);
            user.setPassword(newPass);
            user.setRepeatedPassword(newPass);
            userService.editUser(user);
        } catch (Exception e)
        {
            e.printStackTrace();
            message = e.getMessage();
        }

        model.addAttribute("message", message);

        return "message";
    }
}
