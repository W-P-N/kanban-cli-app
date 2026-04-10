package com.wpn.kanban.parser.commands.util;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.CommandNode;
import com.wpn.kanban.parser.ParsedCommand;

public class HelpCommandNode implements Command {
    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        System.out.println("""
                Help Commands loading.
                """);
    }
}
