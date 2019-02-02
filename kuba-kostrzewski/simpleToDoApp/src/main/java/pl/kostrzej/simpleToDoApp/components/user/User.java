package pl.kostrzej.simpleToDoApp.components.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kostrzej.simpleToDoApp.components.task.Task;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    private String email;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String answer;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

    public User(String login, String password, String email, String question, String answer, List<Task> tasks) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.question = question;
        this.answer = answer;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", tasks size=" + tasks.size() +
                '}';
    }
}
