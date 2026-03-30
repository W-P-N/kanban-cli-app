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
        if(args.length < 2) {
            System.out.println("Usage: create <boardName>");
            return;
        }
        int boardId = (int) Math.floor(Math.random()*10000);
        String boardName = args[1];
        Board newBoard = new Board(boardId, boardName);
        AppState appState = appContext.getAppState();
        appState.addBoard(newBoard);
        return;
    }
}
