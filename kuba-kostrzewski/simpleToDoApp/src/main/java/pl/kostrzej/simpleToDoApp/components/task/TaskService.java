package pl.kostrzej.simpleToDoApp.components.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.components.user.User;
import pl.kostrzej.simpleToDoApp.components.user.UserRepository;

import java.util.Date;

@Service
public class TaskService {

    TaskRepository taskRepository;
    UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public User addTask(User user, String title, String description, Date date, boolean done){
        Task task = new Task();
        task.setUser(user);
        task.setTitle(title);
        task.setDescription(description);
        task.setDate(date);
        task.setDate(date);
        taskRepository.save(task);
        return userRepository.findByLogin(user.getLogin());

    }
}
