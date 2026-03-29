package com.wpn.kanban.cli;

import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.CommandLoader;
import com.wpn.kanban.parser.CommandParser;

import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        boolean isOn = true;
        try {
            Map<String, Command> commands = CommandLoader.loadCommands("src/main/java/com/wpn/kanban/parser/commands.json");
            new CommandParser(commands);
        } catch(Exception e) {
            isOn = false;
            System.out.println("Unable to load commands: " + e);
        }
        while(isOn) {
            String input = scn.next();

            if(input.equals("1")) {
                return;
            }
        }
    }

}
