package com.wpn.kanban.parser;

import java.util.*;

public class ParsedCommand {
    private String commandName;
    private Deque<String> positionalArgs;
    private Map<String, String> namedArgs;

    public ParsedCommand(Deque<String> tokenizedInputArray) {
        this.commandName = tokenizedInputArray.getFirst();
        this.positionalArgs = new ArrayDeque<>();
        this.namedArgs = new HashMap<>();

        while(!tokenizedInputArray.isEmpty()) {
            String currString = tokenizedInputArray.poll();
            if (currString.startsWith("--")) {
                String namedArgValue = tokenizedInputArray.poll();
                if(namedArgValue != null) {
                    this.namedArgs.put(currString.replaceAll("(--)|(=)",""),namedArgValue.replaceAll("\"", ""));
                }
            } else {
                this.positionalArgs.offer(currString);
            }
        }
    }

    public String getCommandName() {
        return commandName;
    }

    public Deque<String> getPositionalArgs() {
        return positionalArgs;
    }

    public Map<String, String> getNamedArgs() {
        return namedArgs;
    }
}
