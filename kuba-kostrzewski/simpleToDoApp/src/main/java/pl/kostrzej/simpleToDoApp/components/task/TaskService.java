package pl.kostrzej.simpleToDoApp.components.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public User addTask(User user, String title, String description, Date date, boolean done){
        log.info("Adding new task to database process initialized.");
        Task task = new Task();
        task.setUser(user);
        task.setTitle(title);
        task.setDescription(description);
        task.setDate(date);
        task.setDate(date);
        log.info("Task to save in database: " + task);
        taskRepository.save(task);
        log.info("Task saved in database successfully.");
        return userRepository.findByLogin(user.getLogin()).get();

    }
}
