package com.wpn.kanban.parser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputTokenizer {
    private final Deque<String> tokenizedInputArray;

    public InputTokenizer(String input) {
        Pattern pattern = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher matcher = pattern.matcher(input);
        tokenizedInputArray = new ArrayDeque<>();
        while(matcher.find()) {
            String matchedGroup = matcher.group();
            if(matchedGroup != null) {
                tokenizedInputArray.offer(matchedGroup);
            }
        }
    }

    public Deque<String> getTokenizedInputArray() {
        return tokenizedInputArray;
    }

}
