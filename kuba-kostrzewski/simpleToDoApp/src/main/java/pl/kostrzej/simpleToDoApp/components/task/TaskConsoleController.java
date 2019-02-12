package pl.kostrzej.simpleToDoApp.components.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.app.DateController;
import pl.kostrzej.simpleToDoApp.components.user.UserService;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Controller
@Slf4j
@AllArgsConstructor
public class TaskConsoleController implements TaskController {

    private Scanner scanner;
    private TaskService taskService;
    private DateController dateController;
    private FieldValidator fieldValidator;


    @Override
    public Task editTask(Task task) {
        log.info("Editing task process initialized.");
        if (task == null){
            return null;
        }
        String title;
        do {
            System.out.println("Tytuł: ");
            System.out.println(task.getTitle());
            System.out.println("Wprowadź nowy tytuł: ");
            title = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(title, "tytuł"));
        task.setTitle(title);
        System.out.println("Opis: ");
        System.out.println(task.getDescription());
        System.out.println("Wprowadź nowy opis lub zostaw puste: ");
        task.setDescription(scanner.nextLine());
        System.out.println("Data: ");
        System.out.println(task.getDate());
        System.out.println("Wprowadź nową datę: ");
        task.setDate(dateController.readDate());
        taskService.saveTask(task);
        log.info("Task edited successfully.");
        return task;
    }
    @Override
    public Task getTaskFromList(List<Task> tasks){
        Integer taskIndex = null;
        showAllTasks(tasks);
        if (tasks.size()>0){
            System.out.println("Podaj nr zadania:");
            try {
                taskIndex = readTaskIndex(tasks);
                return tasks.get(taskIndex);
            } catch (InputMismatchException e){
                log.info("Invalid data type");
                System.out.println("Wpisano niewłaściwe dane! Podaj liczbę!");
            } catch (IndexOutOfBoundsException e){
                System.err.println(e.getMessage());
                log.info("Invalid task number! Task list size= {} Value typed to console = {} {}",
                        tasks.size(), taskIndex, e.getClass().toString());
            }
        }
        return null;
    }
    @Override
    public void deleteTask(Task task) {
        log.info("Deleting task process initialized.");
        taskService.deleteTask(task);
        log.info("Task deleted successfully.");
    }

    @Override
    public Task changeTaskStatus(Task task) {
        log.info("Changing task status process initialized.");
            System.out.println("Czy chcesz zmienić status? (Y/N)");
            try {
                boolean answer = answerYes();
                if (answer && task.getStatus().equals(TaskStatus.DONE)){
                    task.setStatus(TaskStatus.UNDONE);
                    log.info("Task status changed. Status: {}", task.getStatus());
                    taskService.saveTask(task);
                } else if (answer && task.getStatus().equals(TaskStatus.UNDONE)){
                    task.setStatus(TaskStatus.DONE);
                    log.info("Task status changed. Status: {}", task.getStatus());
                    taskService.saveTask(task);
                }
            } catch (InvalidAnswerException e){
                System.err.println(e.getMessage());
                log.info("Invalid answer.");
            }
            log.info("Task status not changed.");
    return task;
    }

    @Override
    public void showAllTasks(List<Task> tasks){
        log.info("Show all tasks process initialized.");
        System.out.println("Lista zadań:");
        if (tasks.isEmpty() || tasks == null){
            log.info("Empty task list");
            System.out.println("Brak zadań");
        } else {
            log.info("Task list size = {}", tasks.size());
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
        log.info("Choosen no out of bounds. Choosen index: {} Table size: {}", taskIndex, tasks.size());
        throw new IndexOutOfBoundsException("Zadanie o nr " + (taskIndex + 1)+ " nie istnieje!");
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
        log.info("Answer invalid {}", answer);
        throw new InvalidAnswerException();
    }
}
