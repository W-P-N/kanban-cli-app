package com.wpn.kanban.parser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpn.kanban.exceptions.kanbanruntimeexceptions.CommandInitiationException;
import com.wpn.kanban.exceptions.kanbanruntimeexceptions.CommandNotFoundException;
import com.wpn.kanban.exceptions.kanbanruntimeexceptions.CommandFileNotFoundException;

public final class CommandLoader {
    private CommandLoader() {};

    public static Map<String, Object> loadCommands(String configPath) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> registry = new HashMap<>();
        Map<String, Object> temp;
        try {
            String json = new String(Files.readAllBytes(Paths.get(configPath)));
            temp = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (IOException e) {
            throw new CommandFileNotFoundException("'commands.json' file not found. Please re-install the application.", e);
        }
        for(Map.Entry<String, Object> entry: temp.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            registry.put(key, loadNode(key,value));
        }
        return registry;
    }

    private static CommandNode loadNode(String key, Object jsonNode) {
        if (jsonNode instanceof String) {
            try {
                Class<?> loadedClass = Class.forName((String) jsonNode);
                return (CommandNode) loadedClass.getDeclaredConstructor().newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException |
                     InvocationTargetException e) {
                throw new CommandInitiationException("Cannot Initiate Command: " + jsonNode, e);
            } catch(ClassNotFoundException e) {
                throw new CommandNotFoundException("Unable to find class: " + jsonNode, e);
            }
            catch (Exception e) {
                throw new CommandNotFoundException("Command Not Found: " + jsonNode, e);
            }
        } else {
            if (jsonNode instanceof HashMap) {
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
