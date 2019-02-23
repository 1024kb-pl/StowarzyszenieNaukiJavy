package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao
{
    void create(User user) throws SQLException;
    User read(String username) throws SQLException;
    void update(User user) throws SQLException;
    void delete(Long user_id) throws SQLException;
    List<User> getAllUsers();
}
