package pl.kostrzej.simpleToDoApp.components.task;

import pl.kostrzej.simpleToDoApp.components.user.User;

public interface TaskController {

    User addTask(User user);
    void showAllUserTasks(User user);

}
