package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.exceptions.kanbanexceptions.BoardAlreadyExistsException;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidBoardNameException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;
import com.wpn.kanban.parser.commands.util.ValidationUtils;

import java.util.Deque;
/**
 Check if ID/Name is empty (omitted or whitespace). Once command values are polled,
 the deque will naturally be empty, so simple parameter validation is sufficient.
 * */
public class CreateBoardCommand implements Command {
    public String getName() {
        return "create";
    }

    public String getDescription() {
        return "Creates a new board. Usage: board create <boardName>";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws BoardAlreadyExistsException, InvalidBoardNameException {
        AppState appState = appContext.getAppState();
        String boardName = parsedCommand.getPositionalArgs().poll();
        boolean boardAdded = appState.addBoard(boardName);
        if(!boardAdded) {
            throw new BoardAlreadyExistsException("Board Already Exists");
        }
        System.out.println("Board created successfully.");
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        return ValidationUtils.requireArgs(parsedCommand,1,"board create <boardName>");
    }
}
