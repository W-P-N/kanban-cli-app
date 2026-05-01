package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.exceptions.kanbanexceptions.InvalidTaskIdException;
import com.wpn.kanban.exceptions.kanbanexceptions.NoActiveBoardException;
import com.wpn.kanban.exceptions.kanbanexceptions.UnableToDeleteTaskException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class DeleteTaskCommand implements Command {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Delete the task using taskId. Usage: task delete <taskId>.";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws InvalidTaskIdException, NoActiveBoardException, UnableToDeleteTaskException {
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            throw new NoActiveBoardException("No Active Board Found. Use 'board open <boardId>' to open the board.");
        }
        String taskId = parsedCommand.getPositionalArgs().poll().trim();
        if(taskId == null) {
            throw new InvalidTaskIdException("Invalid task ID. Please enter valid taskId.");
        }
        if(!activeBoard.deleteTask(taskId)) {
            throw new UnableToDeleteTaskException("Unable to delete task. Task not present in the list. Use 'task list' to get the task list.");
        }
        System.out.println("Task deleted successfully");
    }
}
