package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class CreateTaskCommand implements Command {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Creates a new task. Usage: create --title=<taskName> --description=\"<taskDescription>\"";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {

    }

    public boolean validateArgs(ParsedCommand parsedCommand) {
        return Command.super.validateArgs(parsedCommand);
    }
}
