package com.wpn.kanban.core;

public enum Status {
    TODO,
    DOING,
    FINISHED;

    public Status next() {
        int nextIdx = this.ordinal() + 1;
        if(nextIdx >= Status.values().length) {
            return this;  // For now returning same status. Later add exception
        }
        return Status.values()[nextIdx];
    };

    public Status previous() {
        int prevIdx = this.ordinal() - 1;
        if(prevIdx < 0) {
            return this;  // For now returning same status. Later add exception
        }
        return Status.values()[prevIdx];
    }
}
