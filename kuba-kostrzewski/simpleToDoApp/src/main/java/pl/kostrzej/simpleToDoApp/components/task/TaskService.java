package pl.kostrzej.simpleToDoApp.components.task;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TaskService {

    TaskRepository taskRepository;

    public void addTask(Task task){
        log.info("Adding new task to database process initialized.");
        log.info("Task to save in database: {}", task);
        taskRepository.save(task);
        log.info("Task saved in database successfully.");
    }
    public void deleteTask(Task task){
        log.info("Deleting task from database process initialized.");
        taskRepository.delete(task);
        log.info("Task deleted successfully.");
    }
    public void saveTask(Task task){
        log.info("Saving {} in database process initialized.", task);
        taskRepository.save(task);
        log.info("Task saved in database successfully.");
    }
}
