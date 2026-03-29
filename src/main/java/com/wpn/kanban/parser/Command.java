package com.wpn.kanban.parser;

import java.util.Map;

public interface Command {
    String getName();
    String getDescription();
    void execute(Map<String, String> options);
}
