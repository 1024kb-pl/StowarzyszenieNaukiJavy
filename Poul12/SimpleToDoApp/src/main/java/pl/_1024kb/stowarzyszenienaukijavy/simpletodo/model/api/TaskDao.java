package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao
{
    void create(Task task) throws SQLException;
    List<Task> read(Long userId);
    void update(Task task) throws SQLException;
    void delete(Long taskId) throws SQLException;
    void deleteAllTasks(Long userId) throws SQLException;
}
