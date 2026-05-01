package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidBoardIdException;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidBoardNameException;
import com.wpn.kanban.exceptions.kanbanexceptions.UnableToRenameBoardException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class RenameBoardCommand implements Command {

    @Override
    public String getName() {
        return "rename";
    }

    @Override
    public String getDescription() {
        return "Renames the board. Usage: rename boardId <newBoardName>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws InvalidBoardIdException, InvalidBoardNameException, UnableToRenameBoardException {
        AppState appState = appContext.getAppState();
        String boardId = parsedCommand.getPositionalArgs().poll();
        if(boardId == null) {
            throw new InvalidBoardIdException("Invalid Board Id. Please enter correct board Id.");
        }
        String boardName = parsedCommand.getPositionalArgs().poll().trim();
        if(boardName == null) {
            throw new InvalidBoardNameException("Invalid Board Name. Please enter valid board name.");
        }
        if(!appState.renameBoard(boardId, boardName)) {
            throw new UnableToRenameBoardException("Unable to rename board. Some error occurred.");
        }
        System.out.println("Board " + boardId + " renamed successfully");
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        if(parsedCommand.getPositionalArgs().size() < 2) {
            System.out.println("Usage: rename <boardId> <newBoardName>");
            return false;
        }
        boolean isBoardId = false;
        try{
            Integer.parseInt(parsedCommand.getPositionalArgs().getFirst());
            isBoardId = true;
        } catch(Exception e) {
            System.out.println("Invalid board ID");
        }
        return isBoardId;
    }
}
