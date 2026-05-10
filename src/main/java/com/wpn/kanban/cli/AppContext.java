package com.wpn.kanban.cli;

import java.util.Map;

public final class AppContext {
    private boolean running = true;
    private final AppState appState;
    private Map<String, Object> commandRegistry;
    private final PersistenceManager persistanceManager;

    public AppContext(String filePath) {
        persistanceManager = new PersistenceManager(filePath);
        appState = persistanceManager.load();
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
        this.persistanceManager.save(this.appState);
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }
}
