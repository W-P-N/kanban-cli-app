package com.wpn.kanban.parser;

import java.util.*;

public class ParsedCommand {
    private final Deque<String> positionalArgs;
    private final Map<String, String> namedArgs;
    private final Set<String> unquotedNamedArgs;

    public ParsedCommand(Deque<String> tokenizedInputArray) {
        this.positionalArgs = new ArrayDeque<>();
        this.namedArgs = new HashMap<>();
        this.unquotedNamedArgs = new HashSet<>();

        while(!tokenizedInputArray.isEmpty()) {
            String currString = tokenizedInputArray.poll();
            if(currString.startsWith("--")) {
                if(currString.contains("=")) {
                    String[] parts = currString.split("=", 2);
                    String key = parts[0].replaceAll("--", "");
                    String value = parts.length > 1 ? parts[1] : null;

                    if(value == null || value.isEmpty()) {
                        // value is next token — quoted case
                        // e.g. --desc= "my description"
                        String nextToken = tokenizedInputArray.poll();
                        if(nextToken != null) {
                            boolean isQuoted = nextToken.startsWith("\"") || nextToken.startsWith("'");
                            if(!isQuoted) {
                                unquotedNamedArgs.add(key);
                            }
                            this.namedArgs.put(key, nextToken.replaceAll("\"", ""));
                        }
                    } else {
                        // value is in same token — unquoted case
                        // e.g. --desc=myvalue
                        unquotedNamedArgs.add(key);
                        this.namedArgs.put(key, value);
                    }
                }
            } else {
                this.positionalArgs.offer(currString);
            }
        }
    }

    public Deque<String> getPositionalArgs() {
        return positionalArgs;
    }

    public Map<String, String> getNamedArgs() {
        return namedArgs;
    }

    public boolean isUnquoted(String key) {
        return unquotedNamedArgs.contains(key);
    }
}
