package com.wpn.kanban.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParsedCommand {
    private String commandName;
    private List<String> positionalArgs;
    private Map<String, String> namedArgs;

    public ParsedCommand(String input) {
        String[] inputArray = input.split("\\s+");
        positionalArgs = new ArrayList<>();
        for (String s : inputArray) {
            if (!s.isBlank()) {
                positionalArgs.add(s.trim());
            }
        }
        this.commandName = positionalArgs.getFirst();
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getPositionalArgs() {
        return positionalArgs;
    }

    public Map<String, String> getNamedArgs() {
        return namedArgs;
    }
}
