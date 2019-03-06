package pl.kostrzej.simpleToDoApp.components.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.app.DateController;
import pl.kostrzej.simpleToDoApp.components.task.Task;
import pl.kostrzej.simpleToDoApp.components.task.TaskService;
import pl.kostrzej.simpleToDoApp.components.task.TaskStatus;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;

import java.util.Scanner;

@Controller
@Slf4j
@AllArgsConstructor
public class UserConsoleController implements UserController{

    private UserService userService;
    private TaskService taskService;
    private DateController dateController;
    private Scanner scanner;
    private FieldValidator fieldValidator;


    @Override
    public User addTask(User user){
        Task task = new Task();
        String title;
        log.info("Adding new task process initialized.");
        do {
            System.out.println("Podaj tytuł:");
            title = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(title, "Tytuł"));
        task.setTitle(title);
        System.out.println("Podaj opis lub zostaw puste:");
        task.setDescription(scanner.nextLine());
        task.setDate(dateController.readDate());
        task.setUser(user);
        task.setStatus(TaskStatus.UNDONE);
        taskService.addTask(task);
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
        log.info("User to save: {}", user);
        try{
            userService.addUser(user);
            log.info("User saved successfully.");
            System.out.println("Użytkownik został poprawnie zarejestrowany\n" +
                    "Możesz się teraz zalogować");
        } catch (UserAlreadyExistsException e){
            log.info("User already exists. {}", e.getClass());
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e){
            log.info("Illegal user argument {}", e.getMessage());
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
