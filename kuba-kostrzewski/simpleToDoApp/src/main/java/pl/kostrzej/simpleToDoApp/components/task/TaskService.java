package pl.kostrzej.simpleToDoApp.components.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserRepository;

import java.util.Date;

@Service
@Slf4j
public class TaskService {

    TaskRepository taskRepository;
    UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public User addTask(User user, String title, String description, Date date){
        log.info("Adding new task to database process initialized.");
        Task task = new Task();
        task.setUser(user);
        task.setTitle(title);
        task.setDescription(description);
        task.setDate(date);
        task.setStatus(TaskStatus.UNDONE);
        log.info("Task to save in database: " + task);
        taskRepository.save(task);
        log.info("Task saved in database successfully.");
        return userRepository.findByLogin(user.getLogin()).get();

    }
    public void deleteTask(Task task){
        log.info("Deleting task from database process initialized.");
        taskRepository.delete(task);
        log.info("Task deleted successfully.");
    }
}
