package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.exceptions.InvalidCommandException;

import java.util.Map;

public class CommandParser {
    private final Map<String, CommandNode> commandRegistry;

    public CommandParser(Map<String, CommandNode> commands) {
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
