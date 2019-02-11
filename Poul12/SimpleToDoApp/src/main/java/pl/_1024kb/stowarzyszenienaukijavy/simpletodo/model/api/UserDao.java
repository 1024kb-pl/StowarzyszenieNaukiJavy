package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao
{
    void saveUser(User user) throws SQLException;
    void updateUser(User user, String username) throws SQLException;
    void deleteUser(String username) throws SQLException;
    Optional<User> getUserByUsername(String username) throws SQLException;
    List<User> getAllUsers();
}
