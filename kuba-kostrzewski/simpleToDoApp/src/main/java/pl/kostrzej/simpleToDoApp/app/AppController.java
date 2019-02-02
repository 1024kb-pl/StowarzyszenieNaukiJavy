package pl.kostrzej.simpleToDoApp.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.login.LoginController;
import pl.kostrzej.simpleToDoApp.components.task.TaskController;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserController;

import java.util.Arrays;
import java.util.Scanner;

@Controller
@Slf4j
public class AppController {

    private Scanner scanner;
    private LoginController loginController;
    private UserController userController;
    private TaskController taskController;

    @Autowired
    public AppController(Scanner scanner, LoginController loginController, UserController userController,
                         TaskController taskController) {

        this.scanner = scanner;
        this.loginController = loginController;
        this.userController = userController;
        this.taskController = taskController;
    }
    public void run() {
        boolean exit = false;
        User user = null;
        while (user == null && !exit){
            printWelcomeMenu();
            switch (chooseWelcomeMenuOption()) {
                case LOGIN:
                    user = loginController.logIn();
                    log.info("Logging user finished. User: " + user);
                    break;
                case REGISTER:
                    userController.addUser();
                    log.info("Registration finished.");
                    break;
                case EXIT:
                    log.info("Exit");
                    exit = true;
                    break;
                default:
                    log.info("Undefined option");
                    System.out.println("Podano błędny nr");
            }
        }
        while (!exit){
            printMainMenu(user);
            switch (chooseMainMenuOption()){
                case SHOW_ALL_TASKS:
                    taskController.showAllUserTasks(user);
                    log.info("Show all tasks finished");
                    break;
                case ADD_NEW_TASK:
                    log.info("User's task list size before adding new task = " + user.getTasks().size());
                    user = taskController.addTask(user);
                    log.info("User's task list size after adding new task = " + user.getTasks().size());
                    break;
                case DELETE_TASK:
                    log.info("User's task list size before deleting task = " + user.getTasks().size());
                    user = taskController.deleteTask(user);
                    log.info("User's task list size after deleting task = " + user.getTasks().size());
                    break;
                case EXIT:
                    log.info("Exit");
                    exit = true;
                    break;
                default:
                    log.info("Undefined option");
                    System.out.println("Podano błędny nr");
            }
        }
    }
    private void printMainMenu(User user){
        System.out.println("\nWitaj " + user.getLogin() +"\n");
        Arrays.stream(MainMenuOptions.values()).forEach(System.out::println);
    }
    private void printWelcomeMenu(){
        System.out.println("\nWitaj w aplikacji ZADANIA\n");
        Arrays.stream(WelcomeMenuOptions.values()).forEach(System.out::println);
    }
    private WelcomeMenuOptions chooseWelcomeMenuOption(){
        log.info("Choose welcome menu option process initialized.");
        boolean isOptionCorrect = false;
        WelcomeMenuOptions option = null;
        while (!isOptionCorrect){
            System.out.println("Podaj nr opcji:");
            int optionId = scanner.nextInt();
            scanner.nextLine();
            try {
                option = WelcomeMenuOptions.returnIfCorrect(optionId);
                log.info("Correct option " + option);
                isOptionCorrect = true;
            } catch (InvalidOptionException e) {
                log.info("Incorrect option. " + e.getClass());
                System.err.println(e.getMessage());
            }
        }
        log.info("Choose welcome menu option process finished.");
        return option;
    }
    private MainMenuOptions chooseMainMenuOption(){
        log.info("Choose main menu option process initialized.");
        boolean isOptionCorrect = false;
        MainMenuOptions option = null;
        while (!isOptionCorrect){
            System.out.println("Podaj nr opcji:");
            int optionId = scanner.nextInt();
            scanner.nextLine();
            try {
                option = MainMenuOptions.returnIfCorrect(optionId);
                log.info("Correct option " + option);
                isOptionCorrect = true;
            } catch (InvalidOptionException e) {
                log.info("Incorrect option. " + e.getClass());
                System.err.println(e.getMessage());
            }
        }
        log.info("Choose main menu option process finished");
        return option;
    }
}

