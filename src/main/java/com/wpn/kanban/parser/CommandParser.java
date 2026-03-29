package com.wpn.kanban.parser;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {
    private Map<String,Command> commandRegistry;

    public CommandParser(Map<String,Command> commands) {
        this.commandRegistry = commands;
        registerCommands();
    }

    private void registerCommands() {
        commandRegistry.put("help", new HelpCommand());
    }

    private void showHelp() {

    }
}
