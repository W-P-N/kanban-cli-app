package com.wpn.kanban.exceptions.kanbanruntimeexceptions;

public class CommandNotFoundException extends KanbanRuntimeException{
    public CommandNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
