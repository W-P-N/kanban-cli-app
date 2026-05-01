package com.wpn.kanban.exceptions.kanbanruntimeexceptions;

public class UnableToClearTerminal extends KanbanRuntimeException{
    public UnableToClearTerminal(String message, Exception e) {
        super(message, e);
    }
}
