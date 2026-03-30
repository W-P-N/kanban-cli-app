package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;

import java.util.Map;

public class HelpCommand implements Command {
    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "";
    }

    public void execute(AppContext appContext, String[] args) {
        System.out.println("""
                Help Commands loading.
                """);
    }
}
