package pl.kostrzej.simpleToDoApp.components.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;

import java.util.Scanner;

@Controller
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
        String login, password, email, question, answer;
        do {
            System.out.println("Podaj nazwę użytkownika:");
            login = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(login, "Login"));
        do {
            System.out.println("Podaj hasło:");
            password = scanner.nextLine();
        } while (!isPasswordCorrect(password));
        System.out.println("Podaj e-mail lub zostaw puste:");
        email = scanner.nextLine();
        do {
            System.out.println("Podaj pytanie pomocnicze:");
            question = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(question, "Pytanie pomocnicze"));
        do {
            System.out.println("Podaj odpowiedź:");
            answer = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(answer, "Odpowiedź"));
        try{
            userService.addUser(login, password, email, question, answer);
            System.out.println("Użytkownik został poprawnie zarejestrowany\n" +
                    "Możesz się teraz zalogować");
        } catch (UserAlreadyExistsException e){
            System.err.println(e.getMessage());
        }
    }
    private boolean isPasswordCorrect(String password){
        if (fieldValidator.isFieldEmpty(password, "Hasło")){
            return false;
        }
        System.out.println("Powtórz hasło:");
        if (!password.equals(scanner.nextLine())){
            System.out.println("Podane hasła są różne!");
            return false;
        } else return true;
    }
}
