package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long id;
    @Size(min = 3, message = "{pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task.title.Size}")
    @Column(nullable = false, length = 55)
    private String title;
    @FutureOrPresent(message = "{pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task.date.Future}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @Size(max = 150, message = "{pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task.description.Size}")
    @Column(nullable = false, length = 150)
    private String description;
    @Column(name = "task_done", length = 1, columnDefinition = "tinyint(1) default 0")
    private Boolean taskDone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Task(Long id, String title, LocalDate date, String description, Boolean taskDone, User user) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.taskDone = taskDone;
        this.user = user;
    }
}
