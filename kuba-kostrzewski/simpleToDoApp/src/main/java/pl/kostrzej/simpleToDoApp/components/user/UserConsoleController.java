package pl.kostrzej.simpleToDoApp.components.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;
import pl.kostrzej.simpleToDoApp.components.validator.UserAlreadyExistsException;

import java.util.Scanner;

@Controller
@Slf4j
public class UserConsoleController implements UserController{

    private UserService userService;
    private Scanner scanner;
    private FieldValidator fieldValidator;

    @Autowired
    public UserConsoleController(UserService userService, Scanner scanner, FieldValidator fieldValidator) {
        this.userService = userService;
        this.scanner = scanner;
        this.fieldValidator = fieldValidator;
    }

    @Override
    public void addUser(){
        log.info("Register new user process initialized.");
        User user = new User();
        String login, password, email, question, answer;
        do {
            System.out.println("Podaj nazwę użytkownika:");
            login = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(login, "Login"));
        user.setLogin(login);
        do {
            System.out.println("Podaj hasło:");
            password = scanner.nextLine();
        } while (!isPasswordCorrect(password));
        user.setPassword(password);
        System.out.println("Podaj e-mail lub zostaw puste:");
        email = scanner.nextLine();
        user.setEmail(email);
        do {
            System.out.println("Podaj pytanie pomocnicze:");
            question = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(question, "Pytanie pomocnicze"));
        user.setQuestion(question);
        do {
            System.out.println("Podaj odpowiedź:");
            answer = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(answer, "Odpowiedź"));
        user.setAnswer(answer);
        log.info("User to save: " + user);
        try{
            userService.addUser(user);
            log.info("User saved successfully.");
            System.out.println("Użytkownik został poprawnie zarejestrowany\n" +
                    "Możesz się teraz zalogować");
        } catch (UserAlreadyExistsException e){
            log.info("User already exists." + e.getClass());
            System.err.println(e.getMessage());
        }
    }
    private boolean isPasswordCorrect(String password){
        if (fieldValidator.isFieldEmpty(password, "Hasło")){
            log.info("Password is empty.");
            return false;
        }
        System.out.println("Powtórz hasło:");
        if (!password.equals(scanner.nextLine())){
            log.info("Password not match.");
            System.out.println("Podane hasła są różne!");
            return false;
        } else {
            log.info("Password correct.");
            return true;
        }
    }
}
