package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao
{
    void saveUser(User user, String message) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    List<User> getAllUsers();

}
