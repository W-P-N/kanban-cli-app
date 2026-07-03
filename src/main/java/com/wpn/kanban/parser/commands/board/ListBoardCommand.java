package com.wpn.kanban.parser.commands.board;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.core.Status;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.List;
import java.util.Map;

public class ListBoardCommand implements Command {
    public String getName() {
        return "list";
    }

    public String getDescription() {
        return "Displays the list of kanban boards with their IDs";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        AppState appState = appContext.getAppState();
        List<Board> boardList = appState.getBoardList();
        System.out.println("┌────────┬──────────────────────┬───────┬────────┬──────────┐");
        System.out.println("│   ID   │         NAME         │  TODO │ DOING  │ FINISHED │");
        System.out.println("├────────┼──────────────────────┼───────┼────────┼──────────┤");
        for(Board board: boardList) {
            Map<Status, Integer> taskByStatusCount = board.countTaskByStatus();
            Integer todoTasks = taskByStatusCount.get(Status.TODO);
            Integer doingTasks = taskByStatusCount.get(Status.DOING);
            Integer finishedTasks = taskByStatusCount.get(Status.FINISHED);
            System.out.printf("│ %-6s │ %-20s │ %-5d │ %-6d │ %-8d │%n",
                    board.getBoardId(),
                    board.getBoardName(),
                    todoTasks,
                    doingTasks,
                    finishedTasks);
        }
        System.out.println("└────────┴──────────────────────┴───────┴────────┴──────────┘");
    }
}
