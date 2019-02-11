package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao
{
    void saveTask(Task task, long userId) throws SQLException;
    List<Task> getAllTasksByUserId(long userId);
    void updateCheckTask(String checkTask, long taskId) throws SQLException;
    void updateTask(Task task) throws SQLException;
    void deleteTaskById(long taskId) throws SQLException;
    void deleteAllTasks(long userId) throws SQLException;
}
