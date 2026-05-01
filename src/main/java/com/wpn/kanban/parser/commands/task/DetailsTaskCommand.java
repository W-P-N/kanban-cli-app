package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.core.Task;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidTaskIdException;
import com.wpn.kanban.exceptions.kanbanexceptions.NoActiveBoardException;
import com.wpn.kanban.exceptions.kanbanexceptions.NoTaskFoundException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class DetailsTaskCommand implements Command {
    @Override
    public String getName() {
        return "details";
    }

    @Override
    public String getDescription() {
        return "Get the details of the task with taskId. Usage: task details <taskId>";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws InvalidTaskIdException, NoActiveBoardException, NoTaskFoundException {
        String taskId = parsedCommand.getPositionalArgs().poll().trim();
        if(taskId == null) {
            throw new InvalidTaskIdException("Invalid Task Id. Please enter valid taskId. Use 'task list' to view tasks in this board.");
        }
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            throw new NoActiveBoardException("No Active Board Found. Use 'board open <boardId>' to open board.");
        }
        Task foundTask = activeBoard.findTask(taskId);
        if(foundTask == null) {
            throw new NoTaskFoundException("Task not found for the given taskId: " + taskId +". Add task using 'task add <taskName>' command.");
        }
        System.out.println(foundTask.getTaskDetails(taskId));
    }

}
