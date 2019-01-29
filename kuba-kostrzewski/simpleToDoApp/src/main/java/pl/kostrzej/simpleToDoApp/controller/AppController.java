package pl.kostrzej.simpleToDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.model.User;
import pl.kostrzej.simpleToDoApp.service.LoginService;
import pl.kostrzej.simpleToDoApp.service.TaskService;
import pl.kostrzej.simpleToDoApp.service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class AppController {

    private static final int UNDEFINED = -1;
    private static final int LOGIN = 1;
    private static final int REGISTER = 2;
    private static final int EXIT = 0;
    private static final int SHOW_ALL_TASKS = 1;
    private static final int ADD_NEW_TASK = 2;

    private Scanner scanner;
    private LoginService loginService;
    private UserService userService;
    private TaskService taskService;

    @Autowired
    public AppController(Scanner scanner, LoginService loginService, UserService userService, TaskService taskService) {
        this.scanner = scanner;
        this.loginService = loginService;
        this.userService = userService;
        this.taskService = taskService;
    }
    public void run() {
        boolean exit = false;
        User user = null;
        while (!exit){
            while (user == null && !exit){
                printWelcomeMenu();
                switch (chooseOption()) {
                    case LOGIN:
                        user = loginService.logIn();
                        break;
                    case REGISTER:
                        userService.addUser();
                        break;
                    case EXIT:
                        exit = true;
                        break;
                    default:
                        System.out.println("Podano błędny nr");
                }
        }
            printMainMenu(user);
            switch (chooseOption()){
                case SHOW_ALL_TASKS:
                    taskService.showAllTasks(user);
                    break;
                case ADD_NEW_TASK:
                    user = taskService.addTask(user);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Podano błędny nr");
            }
        }
    }
    private void printMainMenu(User user){
        System.out.println("\nWitaj " + user.getLogin() +"\n" +
                "1. Wyśiwetl wszystkie zadania\n" +
                "2. Dodaj zadanie\n" +
                "0. Wyjście");
    }
    private void printWelcomeMenu(){
        System.out.println("\nWitaj w aplikacji ZADANIA\n" +
            "1. Zaloguj się\n" +
            "2. Zarejestruj się\n" +
            "0. Wyjdź");
    }
    private int chooseOption(){
        int option;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e){
            option = UNDEFINED;
        } finally {
            scanner.nextLine();
        }
        return option;
    }
}

