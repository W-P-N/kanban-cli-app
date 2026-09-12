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
            System.out.println("Error: " + fieldName + " must be a valid number. " + "Usage:" + usage);
            return false;
        }
    }
    public static boolean requireQuoted(ParsedCommand cmd,String flagName,String fieldName,String usage) {
        if (cmd.isUnquoted(flagName)) {
            System.out.println("Error: " + fieldName + " must be a valid quotes. " + "Usage: " + usage);
            return false;
        }
        return true;
    }
    public static boolean requireNonBlank(ParsedCommand cmd,String flagName,String fieldName,String usage) {
        String name = cmd.getNamedArgs().get(flagName);
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: " + fieldName + " cannot be empty. " + "Usage: " + usage);
            return false;
        }
        return true;
    }
}