package pl._1024kb.stowarzyszenienaukijavy.simpletodo.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    void createTask(Task task, String username) throws SQLException;

    List<Task> getAllTasksByUsername(String username);

    void changeTask(Task task, String username) throws SQLException;

    void deleteTaskById(Long taskId) throws SQLException;

    void deleteAllTasks(String username);

    List<Task> getAllTasksByDate(String username, LocalDate dateFilter);

    List<Task> getAllTasksByTaskDone(String username, boolean doneFilter);

    List<Task> getAllTasksOrderedByTitle(String username);

    List<Task> getAllTasksOrderedByDate(String username);

    List<Task> getAllTasksOrderedByStatus(String username);
}
