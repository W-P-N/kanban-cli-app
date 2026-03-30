package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.exceptions.InvalidCommandException;
import com.wpn.kanban.exceptions.PartialCommandException;

import java.util.ArrayList;
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
        String[] inputArray = input.split("\\s+");
        for(int idx=0; idx<inputArray.length; ++idx) {
            inputArray[idx] = inputArray[idx].trim();
        }
        if(!commandRegistry.containsKey(inputArray[0])) {
            throw new InvalidCommandException("Invalid Command");
        }
        Command cmd = commandRegistry.get(inputArray[0]);
        cmd.execute(appContext, inputArray);
    }

}
