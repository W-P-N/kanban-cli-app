package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.exceptions.kanbanexceptions.KanbanException;

public interface Command extends CommandNode {
    String getDescription();
    void execute(AppContext appContext, ParsedCommand parsedCommand) throws KanbanException;
    default boolean validateArgs(ParsedCommand parsedCommand) {
        return true;
    }
}
