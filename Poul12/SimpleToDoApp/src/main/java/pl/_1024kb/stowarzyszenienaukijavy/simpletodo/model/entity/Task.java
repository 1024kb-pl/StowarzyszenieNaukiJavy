package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Task
{
    private Long taskId;
    private String title;
    private LocalDate date;
    private String description;
    private Boolean taskDone;
    private Long userId;
    private User user;

    @Builder
    public Task(Long taskId, String title, LocalDate date, String description, Boolean taskDone, Long userId, User user) {
        this.taskId = taskId;
        this.title = title;
        this.date = date;
        this.description = description;
        this.taskDone = taskDone;
        this.userId = userId;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                '}';
    }
}
