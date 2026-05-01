package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.exceptions.kanbanexceptions.*;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

public class RenameTaskCommand implements Command {
    @Override
    public String getName() {
        return "rename";
    }

    @Override
    public String getDescription() {
        return "Rename the task using taskId. Usage: task rename <taskId>.";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws InvalidTaskIdException, InvalidTaskNameException, NoActiveBoardException, UnableToRenameTaskException {
        String taskId = parsedCommand.getPositionalArgs().poll().trim();
        if(taskId == null) {
            throw new InvalidTaskIdException("Invalid Task Id. Please enter valid taskId");
        }
        String newName = parsedCommand.getPositionalArgs().poll();
        if(newName == null) {
            throw new InvalidTaskNameException("invalid Task Name. Please enter valid taskName");
        }
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            throw new NoActiveBoardException("No Active Board Found. Use 'board open <boardId>' to open the board");
        }
        if(!activeBoard.renameTask(taskId, newName)) {
            throw new UnableToRenameTaskException("Unable to rename task. Unexpected error occurred");
        }
        System.out.println("Task renamed");
    }
}
