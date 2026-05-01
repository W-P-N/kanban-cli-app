package com.wpn.kanban.parser.commands.util;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.Map;

public class HelpCommand implements Command {
    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "Display list of all commands";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        Map<String, Object> cmdRegistry = appContext.getCommandRegistry();

    }
}
