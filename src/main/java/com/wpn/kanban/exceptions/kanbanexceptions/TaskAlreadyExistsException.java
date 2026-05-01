package com.wpn.kanban.exceptions.kanbanexceptions;

public class TaskAlreadyExistsException extends KanbanException {
    public TaskAlreadyExistsException(String message) {
        super(message);
    }
}
