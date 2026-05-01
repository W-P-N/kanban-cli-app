package com.wpn.kanban.exceptions.kanbanexceptions;

public class TaskNotFoundException extends KanbanException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
