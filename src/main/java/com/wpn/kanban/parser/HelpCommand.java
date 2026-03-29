package com.wpn.kanban.parser;

import java.util.Map;

public class HelpCommand implements Command {
    public String getName() {
        return "Help";
    }

    public String getDescription() {
        return"";
    }

    public void execute(Map<String, String> options) {

    }
}
