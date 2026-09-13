package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidBoardIdException;
import com.wpn.kanban.exceptions.kanbanexceptions.UnableToDeleteBoardException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;
import com.wpn.kanban.parser.commands.util.ValidationUtils;

public class DeleteBoardCommand implements Command {

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Deletes the board with the given board ID. Usage: delete board <boardId>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws InvalidBoardIdException, UnableToDeleteBoardException {
        AppState appState = appContext.getAppState();
        String boardId = parsedCommand.getPositionalArgs().poll();

        if(!appState.deleteBoard(boardId)) {
            throw new UnableToDeleteBoardException("Unable to delete board. Unexpected error occurred.");
        }
        System.out.println("Board " + boardId + " deleted successfully");
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        return
                ValidationUtils.requireArgs(
                        parsedCommand,
                        1,
                        "board delete <boardId>"
                ) &&
                ValidationUtils.requireInteger(
                        parsedCommand.getPositionalArgs().getFirst(),
                        "Board ID",
                        "board delete <boardId>"
                );
    }
}
