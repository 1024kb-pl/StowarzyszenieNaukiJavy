package pl.kostrzej.simpleToDoApp.components.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.validator.FieldValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Controller
public class TaskConsoleController implements TaskController {

    private Scanner scanner;
    private TaskService taskService;
    private FieldValidator fieldValidator;

    @Autowired
    public TaskConsoleController(Scanner scanner, TaskService taskService, FieldValidator fieldValidator) {
        this.scanner = scanner;
        this.taskService = taskService;
        this.fieldValidator = fieldValidator;
    }

    @Override
    public User addTask(User user){
        String title, description;
        Date date;
        boolean done;
        do {
            System.out.println("Podaj tytuł:");
            title = scanner.nextLine();
        } while (fieldValidator.isFieldEmpty(title, "Tytuł"));
        System.out.println("Podaj opis lub zostaw puste:");
        description = scanner.nextLine();
        date = readDate();
        done = readDone();
        return taskService.addTask(user, title, description, date, done);
    }

    @Override
    public void showAllUserTasks(User user){
        System.out.println("Lista zadań:");
        List<Task> userTasks = user.getTasks();
        if (userTasks.isEmpty() || userTasks == null){
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
            } catch (ParseException e){
                System.out.println("Podane dane są niewłaściwe!");
            }
        }
        return date;
    }
}
