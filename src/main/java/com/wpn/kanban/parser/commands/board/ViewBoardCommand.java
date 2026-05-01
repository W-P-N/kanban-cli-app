package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.exceptions.kanbanexceptions.NoActiveBoardException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class ViewBoardCommand implements Command {

    @Override
    public String getName() {
        return "view";
    }

    @Override
    public String getDescription() {
        return "Display the view of the currently open board. Usage: board view";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws NoActiveBoardException {
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            throw new NoActiveBoardException("No Active Board found. Open board using 'board open <boardId>'.");
        }
        activeBoard.viewBoard();
    }

}
