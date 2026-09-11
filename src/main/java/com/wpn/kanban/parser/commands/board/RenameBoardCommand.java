package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidBoardIdException;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidBoardNameException;
import com.wpn.kanban.exceptions.kanbanexceptions.UnableToRenameBoardException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;
import com.wpn.kanban.parser.commands.util.ValidationUtils;

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
        String boardName = parsedCommand.getPositionalArgs().poll();
        if(!appState.renameBoard(boardId, boardName)) {
            throw new UnableToRenameBoardException("Unable to rename board. Some error occurred.");
        }
        System.out.println("Board " + boardId + " renamed successfully");
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        if(!ValidationUtils.requireArgs(parsedCommand,2,"rename <boardId> <newBoardName>")){
            return false;
        }
        if(!ValidationUtils.requireInteger(parsedCommand.getPositionalArgs().getFirst(),"Board ID","rename <boardId> <newBoardName>")){
            return false;
        }
        return true;
    }
}
