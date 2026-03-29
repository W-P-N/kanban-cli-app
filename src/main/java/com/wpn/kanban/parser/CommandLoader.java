package com.wpn.kanban.parser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommandLoader {
    public static Map<String,Command> loadCommands(String configPath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Command> registry = new HashMap<>();
        String json = new String(Files.readAllBytes(Paths.get(configPath)));
        Map<String, String> temp;
        temp = objectMapper.readValue(json, new TypeReference<>() {});
        for(Map.Entry<String, String> entry: temp.entrySet()) {
            Class<?> loadedClass = Class.forName(entry.getValue());
            Command cmd = (Command) loadedClass.getDeclaredConstructor().newInstance();
            registry.put(entry.getKey(), cmd);
        }
        return registry;
    }
}
