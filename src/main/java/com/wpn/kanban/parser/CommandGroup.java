package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;

import java.util.Deque;
import java.util.Map;

public class CommandGroup implements CommandNode {
    private String name;
    private Map<String,CommandNode> children;

    public CommandGroup(String name, Map<String,CommandNode> children) {
        this.name = name;
        this.children = children;
    }

    public CommandNode getCommand(Deque<String> positionalArgs) {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }
}
