package com.wpn.kanban.parser;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;

import java.util.List;

public class ListCommand implements Command {
    public String getName() {
        return "list";
    }

    public String getDescription() {
        return "Returns list of kanban boards with their IDs";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        List<Board> boardList = appState.getBoardList();
        for(Board board: boardList) {
            System.out.println(board.getBoardId() + " : " + board.getBoardName());
        }
        return;
    }
}
