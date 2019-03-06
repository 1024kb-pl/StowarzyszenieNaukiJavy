package pl.kostrzej.simpleToDoApp.components.task;


import java.util.List;

public interface TaskController {

    void showAllTasks(List<Task> tasks);
    void deleteTask(Task task);
    Task changeTaskStatus(Task task);
    Task editTask(Task task);
    Task getTaskFromList(List<Task> tasks);
}
