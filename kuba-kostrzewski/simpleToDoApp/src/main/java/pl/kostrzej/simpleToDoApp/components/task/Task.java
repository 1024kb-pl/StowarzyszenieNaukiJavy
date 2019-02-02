package pl.kostrzej.simpleToDoApp.components.task;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kostrzej.simpleToDoApp.components.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    String title;
    @Column(length = 2048)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private boolean done;

    public Task(User user, String title, String description, Date date, boolean done) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.date = date;
        this.done = done;
    }
}
