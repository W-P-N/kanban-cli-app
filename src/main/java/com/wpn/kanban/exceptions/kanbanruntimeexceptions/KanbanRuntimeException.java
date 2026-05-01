package com.wpn.kanban.exceptions.kanbanruntimeexceptions;

public class KanbanRuntimeException extends RuntimeException {
    public KanbanRuntimeException(String message, Exception e) {
        super(message, e);
    }
}
