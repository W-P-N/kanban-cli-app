package com.wpn.kanban.cli;

import com.wpn.kanban.exceptions.InvalidCommandException;
import com.wpn.kanban.parser.CommandNode;
import com.wpn.kanban.parser.CommandLoader;
import com.wpn.kanban.parser.CommandParser;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppContext appContext = new AppContext();
        AppState appState = appContext.getAppState();

        Scanner scn = new Scanner(System.in);
        CommandParser cmdParser = null;
        try {
            Map<String, CommandNode> commands = CommandLoader.loadCommands("src/main/java/com/wpn/kanban/parser/commands.json");
            cmdParser = new CommandParser(commands);
        } catch(Exception e) {
            appContext.stop();
            System.out.println("Unable to load commands: " + e);
        }

        if(cmdParser == null){
            appContext.stop();
        }

        displayWelcome();
        while(appContext.isRunning()) {
            System.out.print("> ");
            String input = scn.nextLine();
            try {
                cmdParser.parse(input, appContext);
            } catch(InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void displayWelcome() {
        System.out.println("""
                     _   __            _                   _____  _     _____\s
                    | | / /           | |                 /  __ \\| |   |_   _|
                    | |/ /  __ _ _ __ | |__   __ _ _ __   | /  \\/| |     | | \s
                    |    \\ / _` | '_ \\| '_ \\ / _` | '_ \\  | |    | |     | | \s
                    | |\\  \\ (_| | | | | |_) | (_| | | | | | \\__/\\| |_____| |_\s
                    \\_| \\_/\\__,_|_| |_|_.__/ \\__,_|_| |_|  \\____/\\_____/\\___/\s
                    """);
        System.out.println("Welcome to Kanban CLI. Type help to see commands.");
    };
}
