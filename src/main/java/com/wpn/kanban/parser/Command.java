package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;

import java.util.Map;

public interface Command {
    String getName();
    String getDescription();
    void execute(AppContext appContext, String[] args);
    default boolean validateArgs(String[] args) {
        return true;
    };
}
