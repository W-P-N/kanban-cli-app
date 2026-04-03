package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.exceptions.InvalidCommandException;
import com.wpn.kanban.exceptions.PartialCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private final Map<String,Command> commandRegistry;

    public CommandParser(Map<String,Command> commands) {
        this.commandRegistry = commands;
    }

    public void parse(String input, AppContext appContext) throws InvalidCommandException{
        if(input == null || input.isBlank()) {
            return;
        }
        ParsedCommand parsedCommand = new ParsedCommand(input);
        if(!commandRegistry.containsKey(parsedCommand.getCommandName())) {
            throw new InvalidCommandException("Invalid Command");
        }
        Command cmd = commandRegistry.get(parsedCommand.getCommandName());
        if(!cmd.validateArgs(parsedCommand)) {
            return;
        }
        cmd.execute(appContext, parsedCommand);
    }

}
