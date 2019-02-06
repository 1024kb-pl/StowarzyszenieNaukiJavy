package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity;

import java.time.LocalDate;

public class Task
{
    private long id;
    private String title;
    private LocalDate date;
    private String description;
    private String taskDone;
    private long username_id;
    private User user;


    public Task(long id, String title, LocalDate date, String description, long username_id, User user) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.username_id = username_id;
        this.user = user;
    }

    public Task(String title, LocalDate date, String description, long username_id, User user) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.username_id = username_id;
        this.user = user;
    }

    public Task(String title, LocalDate date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Task(long id, String title, LocalDate date, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Task(String title, LocalDate date, String description, String taskDone) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.taskDone = taskDone;
    }

    public Task(long id, String title, LocalDate date, String description, String taskDone) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.taskDone = taskDone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getUsername_id() {
        return username_id;
    }

    public void setUsername_id(long username_id) {
        this.username_id = username_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTaskDone() {
        return taskDone;
    }

    public void setTaskDone(String taskDone) {
        this.taskDone = taskDone;
    }
}
