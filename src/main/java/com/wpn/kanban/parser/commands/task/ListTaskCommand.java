package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.core.Status;
import com.wpn.kanban.core.Task;
import com.wpn.kanban.exceptions.kanbanexceptions.NoActiveBoardException;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.Map;

public class ListTaskCommand implements Command {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Lists all the tasks in currently active board. Usage: task list";
    }

    @Override
    public void execute(AppContext appContext, ParsedCommand parsedCommand) throws NoActiveBoardException {
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null) {
            throw new NoActiveBoardException("No Active Board Found. Use 'board open <boardId>' to open the board.");
        }
        Map<String, Task> taskMap = activeBoard.getTaskMap();
        if(taskMap.isEmpty()) {
            System.out.println("No tasks found. Use 'task add <taskName>' to add a task.");
            return;
        }
        System.out.println("┌────────┬──────────────────────┬──────────────────────┬────────────┐");
        System.out.println("│   ID   │         NAME         │      DESCRIPTION     │   STATUS   │");
        System.out.println("├────────┼──────────────────────┼──────────────────────┼────────────┤");

        for(Map.Entry<String, Task> entry: taskMap.entrySet()) {
            String taskId = entry.getKey();
            Task task = entry.getValue();
            System.out.printf("│ %-6s │ %-20s │ %-20s │ %-10s │%n",
                    taskId,
                    task.getTitle(),
                    task.getDescription() == null ? "-" : task.getDescription(),
                    task.getStatus());
        }

        System.out.println("└────────┴──────────────────────┴──────────────────────┴────────────┘");
    }
}
