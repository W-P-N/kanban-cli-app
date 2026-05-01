package com.wpn.kanban.cli;

import com.wpn.kanban.core.Board;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidCommandException;
import com.wpn.kanban.exceptions.kanbanexceptions.KanbanException;
import com.wpn.kanban.exceptions.kanbanruntimeexceptions.*;
import com.wpn.kanban.parser.CommandLoader;
import com.wpn.kanban.parser.CommandParser;

import java.io.IOException;
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
            cmdParser = new CommandParser(appContext, commands);
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

        try {
            clearTerminal();
        } catch(KanbanRuntimeException e) {
            System.out.println(e.getMessage());
        }

        displayWelcome();

        while(appContext.isRunning()) {
            Board activeBoard = appState.getActiveBoard();
            System.out.print((activeBoard == null ? "" : "(" + activeBoard.getBoardName() + ") " ) + "> ");
            String input = scn.nextLine();
            try {
                cmdParser.parse(input, appContext);
            } catch(KanbanException e) {
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

    private static void clearTerminal() {
        try {
            String osName = System.getProperty("os.name");
            if(osName == null) {
                System.out.println("Unable to determine OS. Skipping terminal clear.");
                return;
            }
            if(osName.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch(IOException | InterruptedException e){
            throw new UnableToClearTerminal("Unable to clear terminal.", e);
        }
    }
}
