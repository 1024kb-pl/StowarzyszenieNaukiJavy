package pl.kostrzej.simpleToDoApp.components.task;


import java.util.List;

public interface TaskController {

    void showAllTasks(List<Task> tasks);
    void deleteTask(List<Task> tasks);
    void changeTaskStatus(List<Task> tasks);

}
