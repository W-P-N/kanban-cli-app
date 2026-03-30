package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;

import java.util.Map;

public class QuitCommand implements Command{
    public String getName() {
        return "quit";
    }

    public String getDescription() {
        return "";
    }

    public void execute(AppContext appContext, String[] args) {
        appContext.getAppState().stop();
        return;
    }
}
