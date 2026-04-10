package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.CommandNode;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.Deque;
import java.util.List;

public class CreateBoardCommandNode implements Command {
    public String getName() {
        return "create";
    }

    public String getDescription() {
        return "Creates a new board > create <boardName>";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        int boardId = appState.getBoardIdFromCounter();
        // First element is boardName
        String boardName = parsedCommand.getPositionalArgs().getFirst();
        Board newBoard = new Board(boardId, boardName);
        boolean boardAdded = appState.addBoard(newBoard);
        if(!boardAdded) {
            System.out.println("Board already exists.");
        }
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        Deque<String> commandArray = parsedCommand.getPositionalArgs();
        if(commandArray.size() != 1) {
            System.out.println("Usage: create <boardName>");
            return false;
        }
        return true;
    }
}
