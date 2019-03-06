package pl.kostrzej.simpleToDoApp.app;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.login.LoginController;
import pl.kostrzej.simpleToDoApp.components.task.Task;
import pl.kostrzej.simpleToDoApp.components.task.TaskController;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserController;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
@Slf4j
@AllArgsConstructor
public class AppController {

    private Scanner scanner;
    private LoginController loginController;
    private UserController userController;
    private TaskController taskController;

    public void run() {
        boolean exit = false;
        User user = null;
        while (user == null && !exit){
            printWelcomeMenu();
            switch (chooseWelcomeMenuOption()) {
                case LOGIN:
                    user = loginController.logIn();
                    log.info("Logging user finished. {}", user);
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
                    taskController.showAllTasks(user.getTasks());
                    log.info("Show all tasks finished");
                    break;
                case ADD_NEW_TASK:
                    log.info("User's task list size before adding new task = {}", user.getTasks().size());
                    user = userController.addTask(user);
                    log.info("User's task list size after adding new task = {}", user.getTasks().size());
                    break;
                case DELETE_TASK:
                    log.info("User's task list size before deleting task = {}", user.getTasks().size());
                    System.out.println("USUWANIE ZADANIA");
                    Task taskToDelete = taskController.getTaskFromList(user.getTasks());
                    if (taskToDelete != null){
                        taskController.deleteTask(taskToDelete);
                        user = userController.getUser(user.getId());
                        log.info("User's task list size after deleting task = {}", user.getTasks().size());
                    }
                    break;
                case CHANGE_TASK_STATUS:
                    System.out.println("ZMIANA STATUSU ZADANIA");
                    Task taskToChangeStatus = taskController.getTaskFromList(user.getTasks());
                    log.info("Task before change status: {}", taskToChangeStatus);
                    if (taskToChangeStatus != null){
                        Task editedTask = taskController.changeTaskStatus(taskToChangeStatus);
                        user = userController.getUser(user.getId());
                        log.info("Task after change status: {}", editedTask);
                    }
                    log.info("User's task status changing process finished.");
                    break;
                case EDIT_TASK:
                    System.out.println("EDYCJA ZADANIA");
                    Task taskToEdit = taskController.getTaskFromList(user.getTasks());
                    log.info("Task before edit: {}", taskToEdit);
                    Task editedTask = taskController.editTask(taskToEdit);
                    log.info("Task after edit: {}", editedTask);
                    user = userController.getUser(user.getId());
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
            try {
                int optionId = scanner.nextInt();
                scanner.nextLine();
                option = WelcomeMenuOptions.returnIfCorrect(optionId);
                log.info("Correct option {}", option);
                isOptionCorrect = true;
            } catch (InvalidOptionException e) {
                log.info("Incorrect option. {}", e.getClass());
                System.err.println(e.getMessage());
            } catch (InputMismatchException e){
                scanner.nextLine();
                log.info("Invalid data type. {}", e.getClass());
                System.err.println("Podaj liczbę!");
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
            try {
                int optionId = scanner.nextInt();
                option = MainMenuOptions.returnIfCorrect(optionId);
                log.info("Correct option {}", option);
                isOptionCorrect = true;
            } catch (InvalidOptionException e) {
                log.info("Incorrect option. {}", e.getClass());
                System.err.println(e.getMessage());
            } catch (InputMismatchException e){
                log.info("Invalid data type. {}", e.getClass());
                System.err.println("Podaj liczbę!");
            }
            scanner.nextLine();
        }
        log.info("Choose main menu option process finished");
        return option;
    }
}

