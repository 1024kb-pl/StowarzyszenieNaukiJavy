package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.util.List;

public interface TaskDao
{
    String saveTask(Task task, long userId);
    List<Task> getAllTasksByUserId(long userId);
    void updateCheckTask(String checkTask, long taskId);
    String updateTask(Task task);
    void deleteTaskById(long taskId);
}
