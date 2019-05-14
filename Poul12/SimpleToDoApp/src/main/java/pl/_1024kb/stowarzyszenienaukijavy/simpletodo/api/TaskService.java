package pl._1024kb.stowarzyszenienaukijavy.simpletodo.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    void createTask(Task task, String username) throws Exception;

    List<Task> getAllTasksByUsername(String username);

    void changeTask(Task task, String username) throws Exception;

    void deleteTaskById(Long taskId) throws Exception;

    void deleteAllTasks(String username);

    List<Task> getAllTasksByDate(String username, LocalDate dateFilter);

    List<Task> getAllTasksByTaskDone(String username, boolean doneFilter);

    List<Task> getAllTasksOrderedByTitle(String username);

    List<Task> getAllTasksOrderedByDate(String username);

    List<Task> getAllTasksOrderedByStatus(String username);
}
