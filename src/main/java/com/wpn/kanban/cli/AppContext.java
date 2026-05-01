package com.wpn.kanban.cli;

import java.util.Map;

public final class AppContext {
    private boolean running = true;
    private final AppState appState;
    private Map<String, Object> commandRegistry;

    public AppContext() {
        appState = new AppState();
        commandRegistry = null;
    }

    public AppState getAppState() {
        return appState;
    }

    public Map<String, Object> getCommandRegistry() {
        return commandRegistry;
    }

    public void setCommandRegistry(Map<String,Object> commands) {
        this.commandRegistry = commands;
    }

    public void stop() {
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }
}
