package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;

public interface Command extends CommandNode {
    String getDescription();
    void execute(AppContext appContext, ParsedCommand parsedCommand);
    default boolean validateArgs(ParsedCommand parsedCommand) {
        return true;
    }
}
