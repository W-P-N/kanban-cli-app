package com.wpn.kanban.parser.commands.util;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.CommandNode;
import com.wpn.kanban.parser.ParsedCommand;

public class QuitCommandNode implements Command {
    public String getName() {
        return "quit";
    }

    public String getDescription() {
        return "";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        appContext.stop();
        return;
    }
}
