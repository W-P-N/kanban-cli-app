package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.exceptions.kanbanexceptions.BoardNotFoundException;
import com.wpn.kanban.exceptions.kanbanexceptions.NoActiveBoardException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class CloseBoardCommand implements Command {
    @Override
    public String getName() {
        return "close";
    }

    @Override
    public String getDescription() {
        return "Closes the currently active board. Usage: board close.";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws NoActiveBoardException {
        AppState appState = appContext.getAppState();
        if(!appState.closeActiveBoard()) {
            throw new NoActiveBoardException("No Active Board Found.");
        } else {
            System.out.print("Board closed.");
        }
    }
}
