package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.exceptions.kanbanexceptions.BoardNotFoundException;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidBoardIdException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.List;

public class OpenBoardCommand implements Command {

    @Override
    public String getName() {
        return "open";
    }

    @Override
    public String getDescription() {
        return "Opens the board for board operations. Usage: board open <boardId>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws InvalidBoardIdException, BoardNotFoundException {
        String boardId = parsedCommand.getPositionalArgs().poll().trim();
        if(boardId == null){
            throw new InvalidBoardIdException("Invalid Board Id. Please enter correct board ID.");
        }
        AppState appState = appContext.getAppState();
        if(!appState.setBoardActive(boardId)) {
            throw new BoardNotFoundException("Board not found. Please enter valid board ID. Use 'board list' to get the list of the boards created.");
        }
    }
}
