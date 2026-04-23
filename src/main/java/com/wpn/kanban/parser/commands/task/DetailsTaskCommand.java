package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.core.Task;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class DetailsTaskCommand implements Command {
    @Override
    public String getDescription() {
        return "Get the details of the task with taskId. Usage: task details <taskId>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        String taskId = parsedCommand.getPositionalArgs().poll();
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            System.out.println("No Active Board found");
            return;
        }
        Task foundTask = activeBoard.findTask(taskId);
        if(foundTask == null) {
            System.out.println("No Task with given ID");
            return;
        }
        System.out.println(foundTask.getTaskDetails(taskId));
    }

    @Override
    public String getName() {
        return "Task Details";
    }
}
