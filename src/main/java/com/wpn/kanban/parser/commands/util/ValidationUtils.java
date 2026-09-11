package com.wpn.kanban.parser.commands.util;

import com.wpn.kanban.parser.ParsedCommand;

import java.util.Map;

public final class ValidationUtils {
    public static boolean requireArgs(ParsedCommand cmd, int count,String usage) {
        if(cmd.getPositionalArgs().size() != count) {
            System.out.println("Usage: " + usage);
            return false;
        }
        return true;
    }
    public static boolean requireInteger(String value, String fieldName,String usage) {
        try {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException e) {
            System.out.println("Error: " + fieldName + " must be a valid number. " + "Usage " + usage);
            return false;
        }
    }
    public static boolean requireFor(ParsedCommand cmd,String fieldName,String usage) {
        if (cmd.isUnquoted("desc")) {
            System.out.println("Error " + fieldName + " must be a valid quotes. " + "Usage" + usage);
            return false;
        }
        return true;
    }
}