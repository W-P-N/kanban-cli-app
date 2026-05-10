package com.wpn.kanban.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpn.kanban.exceptions.kanbanruntimeexceptions.KanbanRuntimeException;

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
        try {
            return objectMapper.readValue(new File(filePath), AppState.class);
        } catch (FileNotFoundException e) {
            return new AppState();
        } catch (IOException e) {
            throw new KanbanRuntimeException("Unable to read json file: " + e.getMessage(), e);
        }
    }

    public void save(AppState appState) {
        try {
            objectMapper.writeValue(new File(filePath), appState);
        } catch(IOException e) {
            throw new KanbanRuntimeException("Unable to save state", e);
        }
    }
}
