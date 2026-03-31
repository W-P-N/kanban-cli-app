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

    public void execute(AppContext appContext, String[] args) {
        AppState appState = appContext.getAppState();
        int boardId = appState.getBoardIdFromCounter();
        String boardName = args[1];
        Board newBoard = new Board(boardId, boardName);
        boolean boardAdded = appState.addBoard(newBoard);
        if(!boardAdded) {
            System.out.println("Board already exists.");
        }
    }

    @Override
    public boolean validateArgs(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: create <boardName>");
            return false;
        }
        return true;
    }
}
