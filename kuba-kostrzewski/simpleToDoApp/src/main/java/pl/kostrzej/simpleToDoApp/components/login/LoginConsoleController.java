package pl.kostrzej.simpleToDoApp.components.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;

import java.util.Scanner;

@Controller
@Slf4j
public class LoginConsoleController implements LoginController {

    private Scanner scanner;
    private LoginService loginService;
    private FieldValidator fieldValidator;

    @Autowired
    public LoginConsoleController(Scanner scanner, LoginService loginService, FieldValidator fieldValidator) {
        this.scanner = scanner;
        this.loginService = loginService;
        this.fieldValidator = fieldValidator;
    }

    @Override
    public User logIn() {
        log.info("login process initialized.");
        String login, password;
        do {
            System.out.println("Podaj login:");
            login = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(login, "Login"));
        do {
            System.out.println("Podaj hasło:");
            password = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(password, "Hasło"));
        try {
            User user = loginService.logIn(login, password);
            log.info("Login successful.");
            return user;
        } catch (InvalidLoginDataException e){
            log.info("Invalid login data. " + e.getClass());
            System.err.println(e.getMessage());
            return null;
        }
    }
}
