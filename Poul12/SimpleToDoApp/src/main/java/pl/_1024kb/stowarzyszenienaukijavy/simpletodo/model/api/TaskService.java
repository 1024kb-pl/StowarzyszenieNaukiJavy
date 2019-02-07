package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.util.List;

public interface TaskService
{
    String createTask(Task task, String username);

    List<Task> getAllTasksByUserId(String username);

    String setCheckTask(String checkTask, long taskId);

    String changeTask(Task task);

    String deleteTaskById(long taskId);
}
