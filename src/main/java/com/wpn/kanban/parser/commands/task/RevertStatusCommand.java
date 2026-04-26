package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Task;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class RevertStatusCommand implements Command {
    @Override
    public String getDescription() {
        return "RevertStatus. Usage task revert <taskId>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        String taskId = parsedCommand.getPositionalArgs().poll();
        if(taskId == null){
            System.out.println("No Task ID");
            return;
        }
        AppState appState = appContext.getAppState();
        Task foundTask = appState.getActiveBoard().findTask(taskId);
        if(foundTask == null) {
            System.out.println("No Task Found");
            return;
        }
        foundTask.revertTask();
    }

    @Override
    public String getName() {
        return "";
    }
}
