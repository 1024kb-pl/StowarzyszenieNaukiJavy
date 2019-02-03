package pl.kostrzej.simpleToDoApp.components.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.task.TaskService;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;
import pl.kostrzej.simpleToDoApp.components.validator.UserAlreadyExistsException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Controller
@Slf4j
public class UserConsoleController implements UserController{

    private UserService userService;
    private TaskService taskService;
    private Scanner scanner;
    private FieldValidator fieldValidator;

    @Autowired
    public UserConsoleController(UserService userService, TaskService taskService, Scanner scanner, FieldValidator fieldValidator) {
        this.userService = userService;
        this.taskService = taskService;
        this.scanner = scanner;
        this.fieldValidator = fieldValidator;
    }

    @Override
    public User addTask(User user){
        log.info("Adding new task process initialized.");
        String title, description;
        Date date;
        do {
            System.out.println("Podaj tytuł:");
            title = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(title, "Tytuł"));
        System.out.println("Podaj opis lub zostaw puste:");
        description = scanner.nextLine();
        date = readDate();
        log.info("Date: " + date);
        taskService.addTask(user, title, description, date);
        return getUser(user.getId());
    }

    @Override
    public User getUser(long id) {
        log.info("Getting user process initialized.");
        return userService.getUser(id);
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
    private Date readDate(){
        log.info("Read date process initialized.");
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        while (date == null){
            StringBuilder builder = new StringBuilder();
            System.out.println("Podaj rok w formacie yyyy: ");
            builder.append(scanner.nextLine() + "-");
            System.out.println("Podaj miesiąc w formacie MM: ");
            builder.append(scanner.nextLine() + "-");
            System.out.println("Podaj dzień w formacie dd: ");
            builder.append(scanner.nextLine() + " ");
            System.out.println("Podaj godzinę w formacie HH: ");
            builder.append(scanner.nextLine() + ":");
            System.out.println("Podaj minutę w formacie mm: ");
            builder.append(scanner.nextLine());
            try {
                date = dateFormat.parse(builder.toString());
                log.info("Date format is valid.");
            } catch (ParseException e){
                System.out.println("Podane dane są niewłaściwe!");
                log.info("Date format is invalid. " + e.getClass());
                log.info("Invalid date: " + builder.toString());
            }
        }
        return date;
    }
}
