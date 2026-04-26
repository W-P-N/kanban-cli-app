package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.Deque;

public class CreateBoardCommand implements Command {
    public String getName() {
        return "create";
    }

    public String getDescription() {
        return "Creates a new board > create <boardName>";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        String boardName = parsedCommand.getPositionalArgs().poll();
        boolean boardAdded = appState.addBoard(boardName);
        if(!boardAdded) {
            System.out.println("Board already exists.");
            return;
        }
        System.out.println("Board created successfully.");
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
