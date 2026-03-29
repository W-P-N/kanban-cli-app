package com.wpn.kanban.core;

import com.wpn.kanban.exceptions.InvalidStatusTransitionException;

import java.time.LocalDateTime;

public class Task {
    private String id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String id, String title) {
        this.id = id;
        this.title = title;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void doTask() throws InvalidStatusTransitionException{
        if(this.status != Status.TODO) {
            throw new InvalidStatusTransitionException("Can only start TODO tasks");
        };
        this.status = Status.DOING;
        this.updatedAt = LocalDateTime.now();
    }

    public void finishTask() throws InvalidStatusTransitionException {
        if(this.status != Status.DOING) {
            throw new InvalidStatusTransitionException("Can only finish DOING tasks");
        };
        this.status = Status.FINISHED;
        this.updatedAt= LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
