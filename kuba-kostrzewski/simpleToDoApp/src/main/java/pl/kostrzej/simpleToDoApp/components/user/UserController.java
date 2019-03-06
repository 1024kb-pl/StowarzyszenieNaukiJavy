package pl.kostrzej.simpleToDoApp.components.user;

public interface UserController {
    void addUser();
    User addTask(User user);
    User getUser(long id);
}
