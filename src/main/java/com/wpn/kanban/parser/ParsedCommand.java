package com.wpn.kanban.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsedCommand {
    private String commandName;
    private List<String> positionalArgs;
    private Map<String, String> namedArgs;

    public ParsedCommand(List<String> tokenizedInputArray) {
        this.commandName = tokenizedInputArray.getFirst();
        this.positionalArgs = new ArrayList<>();
        this.namedArgs = new HashMap<>();

        for(int idx=1; idx<tokenizedInputArray.size(); ++idx) {
            String currString = tokenizedInputArray.get(idx);
            if(currString.startsWith("--")) {
                String[] args = currString.split("=");
                args[0] = args[0].substring(2);
                this.namedArgs.put(args[0], args[1]);
            } else {
                this.positionalArgs.add(currString);
            }
        }
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
