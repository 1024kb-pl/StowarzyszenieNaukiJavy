package pl.kostrzej.simpleToDoApp.components.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.user.UserService;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;

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
    public void deleteTask(List<Task> tasks) {
        log.info("Deleting task process initialized.");
        Integer taskToDeleteIndex = null;
        showAllTasks(tasks);
        System.out.println("Podaj nr zadania do usunięcia:");
        try {
            taskToDeleteIndex = readTaskIndex(tasks);
            taskService.deleteTask(tasks.get(taskToDeleteIndex));
            log.info("Task successfully deleted.");
        } catch (InputMismatchException e){
            log.info("Invalid data type");
            System.out.println("Wpisano niewłaściwe dane! Podaj liczbę!");
        } catch (IndexOutOfBoundsException e){
            System.err.println(e.getMessage());
            log.info("Invalid task number! Task list size= " + tasks.size()
                    + " Value typed to console = " + taskToDeleteIndex + " " + e.getCause().toString());
        }
    }

    @Override
    public void changeTaskStatus(List<Task> tasks) {
        log.info("Changing task status process initialized.");
        Integer taskToChangeIndex = null;
        showAllTasks(tasks);
        System.out.println("Podaj nr zadania, którego status chcesz zmienić:");
        try {
            taskToChangeIndex = readTaskIndex(tasks);
            log.info("Task index is valid.");
            Task task = tasks.get(taskToChangeIndex);
            System.out.println("Obecny status zadania: " + task.getStatus());
            log.info("Task status before change: " + task.getStatus());
            System.out.println("Czy chcesz zmienić status? (Y/N)");
            boolean answer = answerYes();
            if (answer && task.getStatus().equals(TaskStatus.DONE)){
                task.setStatus(TaskStatus.UNDONE);
                log.info("Task status changed. Status: " + task.getStatus());
                taskService.saveTask(task);
            } else if (answer && task.getStatus().equals(TaskStatus.UNDONE)){
                task.setStatus(TaskStatus.DONE);
                log.info("Task status changed. Status: " + task.getStatus());
                taskService.saveTask(task);
            }
        } catch (InputMismatchException e){
            log.info("Invalid data type");
            System.out.println("Wpisano niewłaściwe dane! Podaj liczbę!");
        } catch (IndexOutOfBoundsException e){
            System.err.println(e.getMessage());
            log.info("Invalid task number! Task list size= " + tasks.size()
                    + " Value typed to console = " + taskToChangeIndex + " " + e.getCause().toString());
        } catch (InvalidAnswerException e){
            System.err.println(e.getMessage());
            log.info("Invalid answer.");
        }
        log.info("Task status not changed.");
    }

    @Override
    public void showAllTasks(List<Task> tasks){
        log.info("Show all tasks process initialized.");
        System.out.println("Lista zadań:");
        if (tasks.isEmpty() || tasks == null){
            log.info("Empty task list");
            System.out.println("Brak zadań");
        } else {
            log.info("Task list size = " + tasks.size());
            IntStream
                    .range(0, tasks.size())
                    .forEach(i -> {
                                Task task = tasks.get(i);
                                System.out.println(i + 1 + ".\ttytuł: " + task.getTitle() + "\n" +
                                        "\topis: " + task.getDescription() + "\n" +
                                        "\tdata: " + task.getDate() + "\n" +
                                        "\tzakończone: " + task.getStatus());
                            }
                    );
        }
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
