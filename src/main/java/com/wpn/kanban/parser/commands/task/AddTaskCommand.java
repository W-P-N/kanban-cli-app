package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.exceptions.kanbanexceptions.NoActiveBoardException;
import com.wpn.kanban.exceptions.kanbanexceptions.TaskAlreadyExistsException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;
import com.wpn.kanban.parser.commands.util.ValidationUtils;

import java.util.Deque;
import java.util.Map;

public class AddTaskCommand implements Command {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "\tAdd a new task to currently active board. Usage: task add <taskName> --desc=\"<taskDescription>\"";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws NoActiveBoardException, TaskAlreadyExistsException {
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null){
            throw new NoActiveBoardException("No Active Board Found. Use 'board open <boardId>' to activate board.");
        }
        Deque<String> posArgs = parsedCommand.getPositionalArgs();
        Map<String, String> namedArgs = parsedCommand.getNamedArgs();
        if(!activeBoard.addTask(posArgs.poll(), namedArgs.get("desc"))) {
            throw new TaskAlreadyExistsException("Task already exists. Please enter new task name.");
        }
        System.out.println("Task added successfully");
    }

    @Override
    public boolean validateArgs(ParsedCommand parsedCommand) {

        if(!ValidationUtils.requireArgs(parsedCommand,1,"task add <taskName>")){
            return false;
        }
        /**
         * Check if --desc is provided
         * */
        if(parsedCommand.getNamedArgs().containsKey("desc")){
            if(!ValidationUtils.requireQuoted(
                    parsedCommand,
                    "desc",
                    "taskDescription",
                    "task add <taskName> --desc=\"<taskDescription>\"")
            ){
                return false;
            }
            if(!ValidationUtils.requireNonBlank(
                    parsedCommand,
                    "desc",
                    "taskDescription",
                    "task add <taskName> --desc=\"<taskDescription>\"")
            ){
                return false;
            }
        }
        return true;
    }
}
