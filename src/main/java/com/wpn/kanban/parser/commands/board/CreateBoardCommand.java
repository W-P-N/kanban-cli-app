package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.exceptions.kanbanexceptions.BoardAlreadyExistsException;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidBoardNameException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.Deque;

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
        if(boardName == null || boardName.isBlank()) {
            throw new InvalidBoardNameException("Invalid Board Name. Please enter a valid board name");
        }
        boolean boardAdded = appState.addBoard(boardName);
        if(!boardAdded) {
            throw new BoardAlreadyExistsException("Board Already Exists");
        }
        System.out.println("Board created successfully.");
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        Deque<String> commandArray = parsedCommand.getPositionalArgs();
        if(commandArray.size() != 1) {
            System.out.println("Usage: board create <boardName>");
            return false;
        }
        return true;
    }
}
