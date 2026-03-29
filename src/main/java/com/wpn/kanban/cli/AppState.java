package com.wpn.kanban.cli;

public class AppState {
    private boolean running = true;
    public void stop() {
        this.running = false;
    }
    public boolean isRunning() {
        return running;
    }
}
