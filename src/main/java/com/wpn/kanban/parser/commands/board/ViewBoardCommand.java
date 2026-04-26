package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class ViewBoardCommand implements Command {
    @Override
    public String getDescription() {
        return "Gets whole view of the currently open board. Usage: board view";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            System.out.println("No Active Board found.");
            return;
        }
        activeBoard.viewBoard();
    }

    @Override
    public String getName() {
        return "View Board";
    }
}
