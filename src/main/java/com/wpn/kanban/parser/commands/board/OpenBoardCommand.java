package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.List;

public class OpenBoardCommand implements Command {

    @Override
    public String getDescription() {
        return "Opens the board menu for board operations. Usage: board open <boardId>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        String boardIdStr = parsedCommand.getPositionalArgs().poll();
        if(boardIdStr == null){
            System.out.println("Invalid BoardId");
            return;
        }
        int boardId = Integer.parseInt(boardIdStr);
        AppState appState = appContext.getAppState();
        if(!appState.setBoardActive(boardId)) {
            System.out.println("Unable to open board. Please enter the correct details.");
        }
    }

    @Override
    public String getName() {
        return "OpenBoardCommand";
    }
}
