package com.wpn.kanban.exceptions.kanbanexceptions;

public class InvalidStatusTransition extends KanbanException {
    public InvalidStatusTransition(String message) {
        super(message);
    }
}
