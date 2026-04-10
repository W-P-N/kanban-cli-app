package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class RenameBoardCommand implements Command {

    @Override
    public String getName() {
        return "rename";
    }

    @Override
    public String getDescription() {
        return "Renames the command. Usage: rename boardId";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        int boardId = Integer.parseInt(parsedCommand.getPositionalArgs().getFirst());
        String boardName = parsedCommand.getPositionalArgs().poll();
        if(!appState.renameBoard(boardId, boardName)) {
            System.out.println("Unable to rename board " + boardId);
            return;
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
