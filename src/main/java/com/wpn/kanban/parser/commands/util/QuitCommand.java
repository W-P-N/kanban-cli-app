package com.wpn.kanban.parser.commands.util;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class QuitCommand implements Command {
    public String getName() {
        return "quit";
    }

    public String getDescription() {
        return "Saves the state and quits the application.";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        appContext.stop();
        return;
    }
}
