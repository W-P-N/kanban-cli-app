package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class CloseBoardCommand implements Command {
    @Override
    public String getDescription() {
        return "Closes the board menu. Usage: board close";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        if(!appState.closeActiveBoard()) {
            System.out.println("No active board.");
        } else {
            System.out.println("Board closed.");
        }
    }

    @Override
    public String getName() {
        return "CloseBoardCommand";
    }
}
