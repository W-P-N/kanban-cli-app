package com.wpn.kanban.cli;

import com.wpn.kanban.exceptions.kanbanruntimeexceptions.KanbanRuntimeException;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PersistenceManager {
    private final ObjectMapper objectMapper;
    private final String filePath;

    public PersistenceManager(String filePath) {
        this.objectMapper = new ObjectMapper();
        this.filePath = filePath;
    }

    public AppState load() {
        File file = new File(filePath);
        if(!file.exists()) {
            return new AppState();
        }
        try {
            return objectMapper.readValue(file, AppState.class);
        } catch (JacksonException e) {
            throw new KanbanRuntimeException("Unable to read json file: " + e.getMessage(), e);
        }
    }

    public void save(AppState appState) {
        try {
            File file = new File(filePath);
            if(!file.exists()) {
                boolean dirSet = file.getParentFile().mkdirs();
                if(!dirSet) {
                    throw new IOException("Unable to create dir.");
                }
            }
            objectMapper.writeValue(file, appState);
        } catch(IOException e) {
            throw new KanbanRuntimeException("Unable to save state", e);
        }
    }
}
