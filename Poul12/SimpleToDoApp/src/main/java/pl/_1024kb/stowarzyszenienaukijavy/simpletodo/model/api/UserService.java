package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.IncorrectLoginException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.IncorrectPasswordException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService
{
    void createUser(User user) throws Exception;
    void editUser(User user) throws Exception;
    void removeUser(String username) throws SQLException;
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    boolean isUsernameAlreadyExist(String username);
    boolean loginVerification(String username, String password) throws IncorrectLoginException, IncorrectPasswordException;
}
