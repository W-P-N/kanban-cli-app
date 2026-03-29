package com.wpn.kanban.cli;

import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.CommandLoader;
import com.wpn.kanban.parser.CommandParser;

import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        AppContext appContext = new AppContext();
        AppState appState = appContext.getAppState();

        Scanner scn = new Scanner(System.in);
        CommandParser cmdParser = null;
        try {
            Map<String, Command> commands = CommandLoader.loadCommands("src/main/java/com/wpn/kanban/parser/commands.json");
            cmdParser = new CommandParser(commands);
        } catch(Exception e) {
            appState.stop();
            System.out.println("Unable to load commands: " + e);
        }

        if(cmdParser == null){
            appState.stop();
        }

        while(appState.isRunning()) {
            System.out.print("> ");
            String input = scn.next();
            try {
                assert cmdParser != null;
                cmdParser.parse(input, appContext);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }

}
