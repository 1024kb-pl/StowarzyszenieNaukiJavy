package pl.kostrzej.simpleToDoApp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.login.LoginController;
import pl.kostrzej.simpleToDoApp.components.task.TaskController;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserController;

import java.util.Arrays;
import java.util.Scanner;

@Controller
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
                    break;
                case REGISTER:
                    userController.addUser();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("Podano błędny nr");
            }
        }
        while (!exit){
            printMainMenu(user);
            switch (chooseMainMenuOption()){
                case SHOW_ALL_TASKS:
                    taskController.showAllUserTasks(user);
                    break;
                case ADD_NEW_TASK:
                    user = taskController.addTask(user);
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
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
        boolean isOptionCorrect = false;
        WelcomeMenuOptions option = null;
        while (!isOptionCorrect){
            System.out.println("Podaj nr opcji:");
            int optionId = scanner.nextInt();
            scanner.nextLine();
            try {
                option = WelcomeMenuOptions.returnIfCorrect(optionId);
                isOptionCorrect = true;
            } catch (InvalidOptionException e) {
                System.err.println(e.getMessage());
            }
        }

        return option;
    }
    private MainMenuOptions chooseMainMenuOption(){
        boolean isOptionCorrect = false;
        MainMenuOptions option = null;
        while (!isOptionCorrect){
            System.out.println("Podaj nr opcji:");
            int optionId = scanner.nextInt();
            scanner.nextLine();
            try {
                option = MainMenuOptions.returnIfCorrect(optionId);
                isOptionCorrect = true;
            } catch (InvalidOptionException e) {
                System.err.println(e.getMessage());
            }
        }
        return option;
    }
}

