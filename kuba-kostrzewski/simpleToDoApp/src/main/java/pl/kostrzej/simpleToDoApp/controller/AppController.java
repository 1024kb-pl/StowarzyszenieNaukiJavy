package pl.kostrzej.simpleToDoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.model.Task;
import pl.kostrzej.simpleToDoApp.model.User;
import pl.kostrzej.simpleToDoApp.repository.TaskRepository;
import pl.kostrzej.simpleToDoApp.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Controller
public class AppController {

    private Scanner scanner;
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    @Autowired
    public AppController(Scanner scanner, UserRepository userRepository, TaskRepository taskRepository) {
        this.scanner = scanner;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }
    public void run() {
        boolean exit = false;
        User user = null;
        while (!exit){
            while (user == null && !exit){
                switch (welcomeMenu()) {
                    case 1:
                        user = logIn();
                        break;
                    case 2:
                        register();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Podano błędny nr");
                }
        }
            switch (mainMenu(user)){
                case 1:
                   showTaskList(user);
                    break;
                case 2:
                    addTask(user);
                    user = userRepository.findByLogin(user.getLogin());
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Podano błędny nr");
            }
        }
    }
    private int mainMenu(User user){
        System.out.println("\nWitaj " + user.getLogin() +"\n" +
                "1. Wyśiwetl wszystkie zadania\n" +
                "2. Dodaj zadanie\n" +
                "0. Wyjście");
        return readNextInt();
    }
    private void showTaskList(User user){
        System.out.println("Lista zadań:");
        List<Task> userTasks = user.getTasks();
        if (userTasks.size() == 0 || userTasks == null){
            System.out.println("Brak zadań");
        } else {
            IntStream
                    .range(0, userTasks.size())
                    .forEach(i -> {
                                Task task = userTasks.get(i);
                                System.out.println(i + 1 + ".\ttytuł: " + task.getTitle() + "\n" +
                                        "\topis: " + task.getDescription() + "\n" +
                                        "\tdata: " + task.getDate() + "\n" +
                                        "\tzakończone: " + task.isDone());
                            }
                    );
        }
    }
    private void addTask(User user){
        Task taskToAdd = new Task();
        taskToAdd.setUser(user);
        String title;
        do {
            System.out.println("Podaj tytuł:");
            title = scanner.nextLine();
        } while (isStringEmpty(title, "Tytuł"));
        taskToAdd.setTitle(title);
        System.out.println("Podaj opis lub zostaw puste:");
        taskToAdd.setDescription(scanner.nextLine());
        taskToAdd.setDate(readDate());
        taskToAdd.setDone(readDone());
        taskRepository.save(taskToAdd);
    }
    private boolean readDone(){
        do {
            System.out.println("Zadanie wykonanie (T/N)?");
            switch (scanner.nextLine()){
                case "T":
                case "t":
                    return true;
                case "N":
                case "n":
                    return false;
                default:
                    System.out.println("Niepoprawna wartość! Wpisz T lub N");
            }
        }while (true);
    }
    private Date readDate(){
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        StringBuilder builder = new StringBuilder();
        while (date == null){
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
            } catch (ParseException e){
                System.out.println("Podane dane są niewłaściwe!");
            }
        }
        return date;
    }
    private User logIn(){
        String login, password;
        do {
            System.out.println("Podaj login:");
            login = scanner.nextLine();
        } while (isStringEmpty(login, "Login"));
        System.out.println("Podaj hasło:");
        password = scanner.nextLine();
        User user = userRepository.findByLogin(login);
        if (user != null && login.equals(user.getLogin()) && password.equals(user.getPassword())) return user;
        else{
            System.out.println("Niepoprawne dane logowania");
            return null;
        }
    }
    private void register() {
        User user = new User();
        String login, password, question, answer;
        do {
            System.out.println("Podaj nazwę użytkownika:");
            login = scanner.nextLine();
        } while (checkLogin(login));
        user.setLogin(login);
        do {
            System.out.println("Podaj hasło:");
            password = scanner.nextLine();
        } while (checkPassword(password));
        user.setPassword(password);
        System.out.println("Podaj e-mail lub zostaw puste:");
        user.setEmail(scanner.nextLine());
        do {
            System.out.println("Podaj pytanie pomocnicze:");
            question = scanner.nextLine();
        } while (isStringEmpty(question, "Pytanie pomocnicze"));
        user.setQuestion(question);
        do {
            System.out.println("Podaj odpowiedź:");
            answer = scanner.nextLine();
        } while (isStringEmpty(answer, "Odpowiedź"));
        user.setAnswer(answer);
        userRepository.save(user);
        System.out.println("Użytkownik został poprawnie zarejestrowany\n" +
                "Możesz się teraz zalogować");
    }
    private boolean checkLogin(String login) {
        if (isStringEmpty(login, "Login")) {
            return true;
        } else if (userRepository.existsByLogin(login)) {
            System.out.println("Podany login już istnieje!");
            return true;
        } else return false;
    }
    private boolean checkPassword(String password){
        if (isStringEmpty(password, "Hasło")){
            return true;
        }
        System.out.println("Powtórz hasło:");
        if (!password.equals(scanner.nextLine())){
            System.out.println("Podane hasła są różne!");
            return true;
        } else return false;
    }
    private boolean isStringEmpty(String value, String name){
        if (value == null || value.equals("")){
            System.out.println("Pole \"" + name + "\" nie może być puste!");
            return true;
        } else return false;
    }
    private int welcomeMenu(){
        System.out.println("\nWitaj w aplikacji ZADANIA\n" +
            "1. Zaloguj się\n" +
            "2. Zarejestruj się\n" +
            "0. Wyjdź");
        return readNextInt();
    }
    private int readNextInt(){
        int option;
        try {
            option = scanner.nextInt();
        }catch (InputMismatchException e){
            option = 0;
        }finally {
            scanner.nextLine();
        }
        return option;
    }
}

