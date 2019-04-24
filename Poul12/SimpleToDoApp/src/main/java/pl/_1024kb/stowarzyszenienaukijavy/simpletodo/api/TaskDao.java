package pl._1024kb.stowarzyszenienaukijavy.simpletodo.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import java.sql.SQLException;
import java.util.List;

public interface TaskDao {
    void create(Task task) throws SQLException;

    List<Task> getAllByUserId(User user) throws SQLException;

    void update(Task task) throws SQLException;

    void delete(Long taskId) throws SQLException;

    void deleteAllTasks(User user) throws SQLException;
}
