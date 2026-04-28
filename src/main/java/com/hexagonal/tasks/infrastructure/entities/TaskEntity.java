package com.hexagonal.tasks.infrastructure.entities;

import com.hexagonal.tasks.domain.models.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime deletedAt;
    private boolean completed;

    public  TaskEntity(){}

    public TaskEntity(Long id, String title, String description,
    LocalDateTime creationDate, LocalDateTime deletedAt, boolean completed){
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.deletedAt = deletedAt;
        this.completed = completed;

    }

    public static TaskEntity fromDomainModel(Task task){
        return new TaskEntity(task.getId(), task.getTitle(),
                task.getDescription(), task.getCreationDate(),
                task.getDeletedAt(), task.isCompleted());
    }
    public Task toDomainModel(){
        return new Task(id, title,description, creationDate,deletedAt, completed);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDeletedAt(){
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
