package com.wpn.kanban.parser;

import java.util.Map;

public class CommandGroup implements CommandNode {
    private String name;
    private Map<String,CommandNode> children;

    public CommandGroup(String name, Map<String,CommandNode> children) {
        this.name = name;
        this.children = children;
    }

    public Map<String, CommandNode> getChildren() {
        return this.children;
    }

    @Override
    public String getName() {
        return "";
    }
}
