package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity;

import java.time.LocalDate;

public class Task
{
    private Long task_id;
    private String title;
    private LocalDate date;
    private String description;
    private boolean task_done;
    private Long user_id;
    private User user;

    public Task()
    {

    }

    public Task(Long task_id, String title, LocalDate date, String description, Long user_id, User user) {
        this.task_id = task_id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.user_id = user_id;
        this.user = user;
    }

    public Task(String title, LocalDate date, String description, Long user_id, User user) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.user_id = user_id;
        this.user = user;
    }

    public Task(String title, LocalDate date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Task(Long task_id, String title, LocalDate date, String description) {
        this.task_id = task_id;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Task(String title, LocalDate date, String description, boolean task_done) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.task_done = task_done;
    }

    public Task(Long task_id, String title, LocalDate date, String description, boolean task_done) {
        this.task_id = task_id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.task_done = task_done;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getTask_done() {
        return task_done;
    }

    public void setTask_done(boolean task_done) {
        this.task_done = task_done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                '}';
    }
}
