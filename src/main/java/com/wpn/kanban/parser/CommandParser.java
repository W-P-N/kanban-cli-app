package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidCommandException;
import com.wpn.kanban.exceptions.kanbanexceptions.KanbanException;

import java.util.Deque;
import java.util.Map;

public class CommandParser {

    public CommandParser(AppContext appContext, Map<String, Object> commands) {
        appContext.setCommandRegistry(commands);
    }

    public void parse(String input, AppContext appContext) throws KanbanException {
        Map<String, Object> commandRegistry = appContext.getCommandRegistry();

        if(input == null || input.isBlank()) {
            return;
        }
        InputTokenizer inputTokenizer = new InputTokenizer(input);
        ParsedCommand parsedCommand = new ParsedCommand(inputTokenizer.getTokenizedInputArray());
        Deque<String> positionalArgs = parsedCommand.getPositionalArgs();
        Object cmdNodeObj = commandRegistry.get(positionalArgs.poll());
        Command cmd = getCommand(cmdNodeObj, positionalArgs);
        if(cmd == null) {
            throw new InvalidCommandException("Invalid Command. Use 'help' to get command list.");
        }
        if(!cmd.validateArgs(parsedCommand)) {
            return;
        }
        cmd.execute(appContext, parsedCommand);
    }

    private Command getCommand(Object cmdNode, Deque<String> positionalArgs) {
        if(cmdNode == null) {
            return null;
        }
        if(cmdNode instanceof Command) {
            return (Command) cmdNode;
        }
        CommandGroup cmdGrp = (CommandGroup) cmdNode;
        Map<String,CommandNode> cmdChildren = cmdGrp.getChildren();
        Object newCmdNode = cmdChildren.get(positionalArgs.poll());
        if(newCmdNode == null) {
            return null;
        }
        return getCommand(newCmdNode, positionalArgs);
    }

}
