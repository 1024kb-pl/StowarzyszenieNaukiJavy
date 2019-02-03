package pl.kostrzej.simpleToDoApp.components.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserService;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Controller
@Slf4j
public class TaskConsoleController implements TaskController {

    private Scanner scanner;
    private TaskService taskService;
    private UserService userService;
    private FieldValidator fieldValidator;

    @Autowired
    public TaskConsoleController(Scanner scanner, TaskService taskService, UserService userService, FieldValidator fieldValidator) {
        this.scanner = scanner;
        this.taskService = taskService;
        this.userService = userService;
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
        return taskService.addTask(user, title, description, date);
    }

    @Override
    public User deleteTask(User user) {
        log.info("Deleting task process initialized.");
        Integer taskToDeleteIndex = null;
        showAllUserTasks(user);
        System.out.println("Podaj nr zadania do usunięcia:");
        try {
            taskToDeleteIndex = readTaskIndex(user.getTasks());
            taskService.deleteTask(user.getTasks().get(taskToDeleteIndex));
            log.info("Task successfully deleted.");
            return userService.getUser(user.getId());
        } catch (InputMismatchException e){
            log.info("Invalid data type");
            System.out.println("Wpisano niewłaściwe dane! Podaj liczbę!");
            return user;
        } catch (IndexOutOfBoundsException e){
            System.err.println(e.getMessage());
            log.info("Invalid task number! Task list size= " + user.getTasks().size()
                    + " Value typed to console = " + taskToDeleteIndex + " " + e.getCause().toString());
            return user;
        }
    }

    @Override
    public User changeTaskStatus(User user) {
        log.info("Changing task status process initialized.");
        Integer taskToChangeIndex = null;
        showAllUserTasks(user);
        System.out.println("Podaj nr zadania, którego status chcesz zmienić:");
        try {
            taskToChangeIndex = readTaskIndex(user.getTasks());
            log.info("Task index is valid.");
            Task task = user.getTasks().get(taskToChangeIndex);
            System.out.println("Obecny status zadania: " + task.getStatus());
            log.info("Task status before change: " + task.getStatus());
            System.out.println("Czy chcesz zmienić status? (Y/N)");
            boolean answer = answerYes();
            if (answer && task.getStatus().equals(TaskStatus.DONE)){
                task.setStatus(TaskStatus.UNDONE);
                log.info("Task status changed. Status: " + task.getStatus());
                taskService.saveTask(task);
                return userService.getUser(user.getId());
            } else if (answer && task.getStatus().equals(TaskStatus.UNDONE)){
                task.setStatus(TaskStatus.DONE);
                log.info("Task status changed. Status: " + task.getStatus());
                taskService.saveTask(task);
                return userService.getUser(user.getId());
            }
        } catch (InputMismatchException e){
            log.info("Invalid data type");
            System.out.println("Wpisano niewłaściwe dane! Podaj liczbę!");
        } catch (IndexOutOfBoundsException e){
            System.err.println(e.getMessage());
            log.info("Invalid task number! Task list size= " + user.getTasks().size()
                    + " Value typed to console = " + taskToChangeIndex + " " + e.getCause().toString());
        } catch (InvalidAnswerException e){
            System.err.println(e.getMessage());
            log.info("Invalid answer.");
        }
        log.info("Task status not changed.");
        return user;
    }

    @Override
    public void showAllUserTasks(User user){
        log.info("Show all tasks process initialized.");
        System.out.println("Lista zadań:");
        List<Task> userTasks = user.getTasks();
        if (userTasks.isEmpty() || userTasks == null){
            log.info("Empty task list");
            System.out.println("Brak zadań");
        } else {
            log.info("Task list size = " + userTasks.size());
            IntStream
                    .range(0, userTasks.size())
                    .forEach(i -> {
                                Task task = userTasks.get(i);
                                System.out.println(i + 1 + ".\ttytuł: " + task.getTitle() + "\n" +
                                        "\topis: " + task.getDescription() + "\n" +
                                        "\tdata: " + task.getDate() + "\n" +
                                        "\tzakończone: " + task.getStatus());
                            }
                    );
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

    private int readTaskIndex(List<Task> tasks){
        int taskIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        log.info("Correct data type.");
        if (taskIndex<tasks.size() || taskIndex < 0) {
            log.info("Correct index value");
            return taskIndex;
        }
        log.info("Choosen no out of bounds. Choosen index: " + taskIndex + "Table size: " + tasks.size());
        throw new IndexOutOfBoundsException("Zadanie o nr " + taskIndex + " nie istnieje!");
    }
    private boolean answerYes(){
        String answer = scanner.nextLine();
        if (answer.toUpperCase().equals("Y")){
            log.info("Answer valid Y");
            return true;
        }
        if (answer.toUpperCase().equals("N")){
            log.info("Answer valid N");
            return false;
        }
        log.info("Answer invalid " + answer);
        throw new InvalidAnswerException();
    }
}
