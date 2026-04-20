package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class ListTaskCommand implements Command {

    @Override
    public String getDescription() {
        return "Lists all the tasks in currently active board. Usage: task list";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            System.out.println("No active board found");
        } else {
            activeBoard.listTask();
        }
    }

    @Override
    public String getName() {
        return "List Task";
    }
}
