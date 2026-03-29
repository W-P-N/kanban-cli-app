package com.wpn.kanban.cli;

public class AppContext {
    private AppState appState;
    private Config config;

    public AppContext() {
        appState = new AppState();
        config = new Config();
    }

    public AppState getAppState() {
        return appState;
    }
}
