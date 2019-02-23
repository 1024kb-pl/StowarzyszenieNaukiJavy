package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface TaskService
{
    void createTask(Task task, String username) throws SQLException;

    List<Task> getAllTasksByUserId(String username);

    void changeTask(Task task) throws SQLException;

    void deleteTaskById(Long taskId) throws SQLException;

    void deleteAllTasks(String username);

    List<Task> getAllTasksByDate(String username, LocalDate dateFilter);

    List<Task> getAllTasksByTaskDone(String username, boolean doneFilter);

    List<Task> getAllTasksOrderedByTitle(String username);

    List<Task> getAllTasksOrderedByDate(String username);

    List<Task> getAllTasksOrderedByStatus(String username);
}
