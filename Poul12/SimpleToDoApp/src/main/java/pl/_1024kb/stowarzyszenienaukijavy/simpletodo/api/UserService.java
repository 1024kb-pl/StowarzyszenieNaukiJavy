package pl._1024kb.stowarzyszenienaukijavy.simpletodo.api;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.IncorrectLoginException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.IncorrectPasswordException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{

    void createUser(User user) throws Exception;

    void editUser(User user) throws Exception;

    void removeUser(String username) throws Exception;

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers();

    boolean isUsernameAlreadyExist(String username);

    boolean isEmailAlreadyExist(String email);

    boolean loginVerification(String username, String password) throws IncorrectLoginException, IncorrectPasswordException;
}
