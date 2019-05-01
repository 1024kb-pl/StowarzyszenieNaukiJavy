package pl._1024kb.stowarzyszenienaukijavy.simpletodo.util;

import org.springframework.ui.Model;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

public class EntityCreator {
    private String[] keys;

    public EntityCreator() {
    }

    public Task createTask(Model model) {
        keys = new String[4];
        setKeys(model);
        System.out.println("keys " + Arrays.toString(keys));
        return Task.builder()
                .title(keys[0])
                .date(LocalDate.parse(keys[1]))
                .description(keys[2])
                .taskDone(Boolean.valueOf(keys[3]))
                .build();
    }

    public Task updateTask(Model model) {
        keys = new String[5];
        setKeys(model);
        return Task.builder()
                .taskId(Long.parseLong(keys[0]))
                .title(keys[1])
                .date(LocalDate.parse(keys[2]))
                .description(keys[3])
                .taskDone(Boolean.valueOf(keys[4]))
                .build();
    }

    public User createUser(Model model) {
        keys = new String[4];
        setKeys(model);
        return User.builder()
                .username(keys[0])
                .email(keys[1])
                .password(keys[2])
                .repeatedPassword(keys[3])
                .build();
    }

    public User updateUser(Model model)
    {
        keys = new String[5];
        setKeys(model);
        return User.builder()
                .userId(Long.parseLong(keys[0]))
                .username(keys[1])
                .email(keys[2])
                .password(keys[3])
                .repeatedPassword(keys[4])
                .build();
    }

    private void setKeys(Model model) {
        Object value;
        int i = 0;
        for (Map.Entry<String, Object> entry : model.asMap().entrySet()) {
            value = entry.getValue();
            keys[i] = (String) value;
            //System.out.println(i + keys[i]);
            i++;
        }
    }
}
