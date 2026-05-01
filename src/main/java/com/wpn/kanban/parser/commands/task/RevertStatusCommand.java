package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Task;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidTaskIdException;
import com.wpn.kanban.exceptions.kanbanexceptions.TaskNotFoundException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class RevertStatusCommand implements Command {
    @Override
    public String getName() {
        return "revert";
    }

    @Override
    public String getDescription() {
        return "Moves task from current status to previous status. Status: FINISHED -> DOING -> TODO. Usage task revert <taskId>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws InvalidTaskIdException, TaskNotFoundException {
        String taskId = parsedCommand.getPositionalArgs().poll().trim();
        if(taskId == null){
            throw new InvalidTaskIdException("Invalid Task ID. Use 'task list' to get the list of tasks in the active board.");
        }
        AppState appState = appContext.getAppState();
        Task foundTask = appState.getActiveBoard().findTask(taskId);
        if(foundTask == null) {
            throw new TaskNotFoundException("Task not found. Use 'task list' to get the list of tasks.");
        }
        foundTask.revertTask();
    }
}
