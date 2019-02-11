package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService
{
    String createTask(Task task, String username);

    List<Task> getAllTasksByUserId(String username);

    String changeTask(Task task);

    String deleteTaskById(long taskId);

    void deleteAllTasks(String username);

    List<Task> getAllTasksByDate(String username, LocalDate dateFilter);

    List<Task> getAllTasksByTaskDone(String username, String doneFilter);

    List<Task> getAllTasksOrderedByTitle(String username);

    List<Task> getAllTasksOrderedByDate(String username);

    List<Task> getAllTasksOrderedByStatus(String username);
}
