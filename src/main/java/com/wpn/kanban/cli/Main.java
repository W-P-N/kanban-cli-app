package com.wpn.kanban.cli;

import com.wpn.kanban.core.Board;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidCommandException;
import com.wpn.kanban.exceptions.kanbanruntimeexceptions.CommandFileNotFoundException;
import com.wpn.kanban.exceptions.kanbanruntimeexceptions.CommandInitiationException;
import com.wpn.kanban.exceptions.kanbanruntimeexceptions.CommandNotFoundException;
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
        displayLoadingCommands();
        try {
            Map<String, Object> commands = CommandLoader.loadCommands("src/main/java/com/wpn/kanban/parser/commands.json");
            cmdParser = new CommandParser(commands);
        } catch(CommandInitiationException | CommandFileNotFoundException | CommandNotFoundException e) {
            System.out.println("Loading Commands failed: " + e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Unable to load commands. Unexpected Error: " + e.getMessage());
        }

        if(cmdParser == null){
            System.out.println("Please restart/ reinstall the application.");
            appContext.stop();
            return;
        }

        displayWelcome();

        while(appContext.isRunning()) {
            Board activeBoard = appState.getActiveBoard();
            System.out.print((activeBoard == null ? "" : "(" + activeBoard.getBoardId() + ") " ) + "> ");
            String input = scn.nextLine();
            try {
                cmdParser.parse(input, appContext);
            } catch(InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void displayWelcome() {
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

    private static void displayLoadingCommands () {
        System.out.println("Loading Commands....");
        System.out.println("Reading Command File....");
    }
}
