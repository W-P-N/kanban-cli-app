package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String, Object> commandRegistry;

    public CommandParser(Map<String, Object> commands) {
        this.commandRegistry = commands;
    }

    public void parse(String input, AppContext appContext) throws InvalidCommandException{
        if(input == null || input.isBlank()) {
            return;
        }
        InputTokenizer inputTokenizer = new InputTokenizer(input);
        ParsedCommand parsedCommand = new ParsedCommand(inputTokenizer.getTokenizedInputArray());
        CommandGroup cmdGroup = new CommandGroup(parsedCommand.getCommandName(), null);
        Command cmd = (Command) cmdGroup.getCommand(parsedCommand.getPositionalArgs());
        if(!cmd.validateArgs(parsedCommand)) {
            return;
        }
        cmd.execute(appContext, parsedCommand);
    }

}
