package com.wpn.kanban.parser;

import java.util.*;

public class InputTokenizer {
    private List<String> tokenizedInputArray;
    private List<String> positionalArgs;
    private Map<String,String> namedArgs;

    public InputTokenizer(String input) {
        String[] inputArray = input.split("\\s+");
        tokenizedInputArray = new ArrayList<>();
        for (String s : inputArray) {
            String newS = s.trim();
            if (!newS.isBlank()) {
                tokenizedInputArray.add(s.trim());
            }
        }
    }

    public List<String> getTokenizedInputArray() {
        return tokenizedInputArray;
    }
    
}
