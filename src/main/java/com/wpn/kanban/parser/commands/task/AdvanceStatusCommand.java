package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Task;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidStatusTransition;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidTaskIdException;
import com.wpn.kanban.exceptions.kanbanexceptions.TaskNotFoundException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;
import com.wpn.kanban.parser.commands.util.ValidationUtils;

public class AdvanceStatusCommand implements Command {
    @Override
    public String getDescription() {
        return "Moves task from current status to next status. Status: TODO -> DOING -> FINISHED. Usage task advance <taskId>.";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws InvalidTaskIdException, TaskNotFoundException, InvalidStatusTransition {
        String taskId = parsedCommand.getPositionalArgs().poll();
        AppState appState = appContext.getAppState();
        Task foundTask = appState.getActiveBoard().findTask(taskId);
        if(foundTask == null) {
            throw new TaskNotFoundException("Task not found. Use 'task list' to get the list of tasks.");
        }
        if(!foundTask.advanceTask()) {
            throw new InvalidStatusTransition("Task is already FINISHED. Cannot advance.");
        }
        System.out.println("Task advance successfully");
    }

    @Override
    public String getName() {
        return "advance";
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {
        if(!ValidationUtils.requireArgs(parsedCommand,1,"task advance <taskId>")){
            return false;
        }
        if(!ValidationUtils.requireInteger(parsedCommand.getPositionalArgs().getFirst(),"Task ID","task advance <taskId>")){
            return false;
        }
        return  true;
    }
}
