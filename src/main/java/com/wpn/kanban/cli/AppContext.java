package com.wpn.kanban.cli;

public final class AppContext {
    private boolean running = true;
    private AppState appState;

    public AppContext() {
        appState = new AppState();
    }

    public AppState getAppState() {
        return appState;
    }

    public void stop() {
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }
}
