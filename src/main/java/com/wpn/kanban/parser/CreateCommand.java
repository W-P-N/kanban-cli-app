package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;

public class CreateCommand implements Command {
    public String getName() {
        return "create";
    }

    public String getDescription() {
        return "Creates a new board > create <boardName>";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        int boardId = appState.getBoardIdFromCounter();
        String boardName = parsedCommand.getPositionalArgs().get(1);
        Board newBoard = new Board(boardId, boardName);
        boolean boardAdded = appState.addBoard(newBoard);
        if(!boardAdded) {
            System.out.println("Board already exists.");
        }
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        if(parsedCommand.getPositionalArgs().size() < 2) {
            System.out.println("Usage: create <boardName>");
            return false;
        }
        return true;
    }
}
