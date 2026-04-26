package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class RenameTaskCommand implements Command {
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
        String newName = parsedCommand.getPositionalArgs().poll();
        if(newName == null) {
            System.out.println("Invalid task name.");
            return;
        }
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(!activeBoard.renameTask(taskId, newName)) {
            System.out.println("Unable to rename task.");
            return;
        }
        System.out.println("Task renamed");
    }

    @Override
    public String getName() {
        return "";
    }
}
