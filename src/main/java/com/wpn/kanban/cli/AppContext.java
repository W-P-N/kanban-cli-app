package com.wpn.kanban.cli;

import java.util.Map;

public final class AppContext {
    private boolean running = true;
    private final AppState appState;
    private Map<String, Object> commandRegistry;
    private final PersistenceManager persistenceManager;

    public AppContext(String filePath) {
        persistenceManager = new PersistenceManager(filePath);
        appState = persistenceManager.load();
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
        this.persistenceManager.save(this.appState);
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }
}
