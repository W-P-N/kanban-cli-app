package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class DeleteTaskCommand implements Command {
    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        String taskId = parsedCommand.getPositionalArgs().poll();
        if(taskId == null) {
            System.out.println("Invalid TaskId");
            return;
        }
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            System.out.println("No Active Board");
            return;
        }
        if(!activeBoard.deleteTask(taskId)) {
            System.out.println("Unable to delete task.");
            return;
        }
        System.out.println("Task deleted successfully");
    }

    @Override
    public String getName() {
        return "";
    }
}
