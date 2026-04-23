package com.wpn.kanban.parser.commands.task;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.cli.AppState;
import com.wpn.kanban.core.Board;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.Deque;
import java.util.List;
import java.util.Map;

public class AddTaskCommand implements Command {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Add a new task to currently active board. Usage: task add <taskName> --desc=\"<taskDescription>\"";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        Deque<String> posArgs = parsedCommand.getPositionalArgs();
        Map<String, String> namedArgs = parsedCommand.getNamedArgs();
        AppState appState = appContext.getAppState();
        Board activeBoard = appState.getActiveBoard();
        if(activeBoard == null){
            System.out.println("No Active board found");
        } else {
            activeBoard.addTask(posArgs.poll(), namedArgs.get("desc"));
        }
//        Map<String, String> nameArgs = parsedCommand.getNamedArgs();
//        for(Map.Entry<String,String> entry: nameArgs.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
    }
}
