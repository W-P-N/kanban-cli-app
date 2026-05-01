package com.wpn.kanban.exceptions.kanbanruntimeexceptions;

public class CommandFileNotFoundException extends KanbanRuntimeException{
    public CommandFileNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
