package pl.kostrzej.simpleToDoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.model.Task;
import pl.kostrzej.simpleToDoApp.model.User;
import pl.kostrzej.simpleToDoApp.repository.TaskRepository;
import pl.kostrzej.simpleToDoApp.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Service
public class TaskService {

    Scanner scanner;
    TaskRepository taskRepository;
    UserRepository userRepository;

    @Autowired
    public TaskService(Scanner scanner, TaskRepository taskRepository, UserRepository userRepository) {
        this.scanner = scanner;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public User addTask(User user){
        Task taskToAdd = new Task();
        taskToAdd.setUser(user);
        String title;
        do {
            System.out.println("Podaj tytuł:");
            title = scanner.nextLine();
        } while (isFieldEmpty(title, "Tytuł"));
        taskToAdd.setTitle(title);
        System.out.println("Podaj opis lub zostaw puste:");
        taskToAdd.setDescription(scanner.nextLine());
        taskToAdd.setDate(readDate());
        taskToAdd.setDone(readDone());
        taskRepository.save(taskToAdd);
        return userRepository.findByLogin(user.getLogin());
    }
    public void showAllTasks(User user){
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
    private boolean isFieldEmpty(String value, String name){
        if (value == null || value.equals("")){
            System.out.println("Pole \"" + name + "\" nie może być puste!");
            return true;
        } else return false;
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
}
