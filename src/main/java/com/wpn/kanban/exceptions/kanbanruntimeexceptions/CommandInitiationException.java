package com.wpn.kanban.exceptions.kanbanruntimeexceptions;

public class CommandInitiationException extends KanbanRuntimeException{
    public CommandInitiationException(String message, Exception e) {
        super(message, e);
    }
}
