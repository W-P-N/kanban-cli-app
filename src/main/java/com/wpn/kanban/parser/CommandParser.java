package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String,Command> commandRegistry;

    public CommandParser(Map<String,Command> commands) {
        this.commandRegistry = commands;
    }

    public void parse(String input, AppContext appContext) throws InvalidCommandException {
        if(!commandRegistry.containsKey(input)) {
            throw new InvalidCommandException("Invalid Command");
        }
        String sanitizedInput = input.trim();
        Command cmd = commandRegistry.get(sanitizedInput);
        cmd.execute(appContext); // Accepts Map<String,String> args;
        // What next?
    }

}
