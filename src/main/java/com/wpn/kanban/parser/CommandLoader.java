package com.wpn.kanban.parser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class CommandLoader {
    private CommandLoader() {};
    private static Map<String,Object> registry;

    public static Map<String, Object> loadCommands(String configPath) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        registry = new HashMap<>();
        String json = new String(Files.readAllBytes(Paths.get(configPath)));
        Map<String, Object> temp;
        temp = objectMapper.readValue(json, new TypeReference<>() {});
        for(Map.Entry<String, Object> entry: temp.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            registry.put(key, loadNode(key,value));
        }
        return registry;
    }

    private static CommandNode loadNode(String key, Object jsonNode) throws Exception {
        if (jsonNode instanceof String) {
            Class<?> loadedClass = Class.forName((String) jsonNode);
            CommandNode cmd = (CommandNode) loadedClass.getDeclaredConstructor().newInstance();
            return cmd;
        } else {
            if (jsonNode instanceof HashMap<?, ?>) {
                Map<String, Object> nestedJson = (HashMap<String, Object>) jsonNode;
                Map<String, CommandNode> children = new HashMap<>();
                for (Map.Entry<String, Object> entry : nestedJson.entrySet()) {
                    String childKey = entry.getKey();
                    Object value = entry.getValue();
                    children.put(childKey, loadNode(childKey, value));
                }
                return new CommandGroup(key, children);
            }
        }
        return new CommandGroup(key, new HashMap<>());
    }
}
