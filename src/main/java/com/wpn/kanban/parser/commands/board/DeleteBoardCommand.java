package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class DeleteBoardCommand implements Command {

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Deletes the board based on board ID. Usage: delete board <boardId>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        int boardId = Integer.parseInt(parsedCommand.getPositionalArgs().getFirst());
        if(!appState.deleteBoard(boardId)) {
            System.out.println("Unable to delete board " + boardId);
            return;
        }
        System.out.println("Board " + boardId + " deleted successfully");
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        if(parsedCommand.getPositionalArgs().isEmpty()) {
            System.out.println("Usage: delete <boardId>");
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
