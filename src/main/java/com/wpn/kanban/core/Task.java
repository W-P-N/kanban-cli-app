package com.wpn.kanban.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Task {
    private String id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonCreator
    public Task(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title
    )
    {
        this.id = id;
        this.title = title;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public boolean advanceTask() {
        Status prevStatus = this.getStatus();
        Status newStatus = this.getStatus().next();
        if(newStatus == prevStatus) {
            return false;
        }
        this.setStatus(newStatus);
        return true;
    }

    public boolean revertTask() {
        Status prevStatus = this.getStatus();
        Status newStatus = this.getStatus().previous();
        if(newStatus == prevStatus) {
            return false;
        }
        this.setStatus(newStatus);
        return true;
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

    public String getTaskDetails(String taskId) {

        return "TaskId: " + taskId
                + "\nTaskName: " + title
                + "\nDescription: " + (description == null ? "NA" : description)
                + "\nCreated At: " + createdAt
                + "\nUpdated At: " + updatedAt
                ;
    }
}
