package com.wpn.kanban.parser.commands.util;

import com.wpn.kanban.cli.AppContext;
import com.wpn.kanban.parser.Command;
import com.wpn.kanban.parser.CommandGroup;
import com.wpn.kanban.parser.CommandNode;
import com.wpn.kanban.parser.ParsedCommand;

import java.util.Map;

public class HelpCommand implements Command {
    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "Display list of all commands";
    }

    public void execute(AppContext appContext, ParsedCommand parsedCommand) {
        System.out.println("""
                ╔════════════════════════════════════════════╗
                ║           KANBAN CLI - HELP                ║
                ╚════════════════════════════════════════════╝
                
                A lightweight command-line Kanban board manager.
                Organize your work with boards and tasks, track\s
                progress from TODO → DOING → FINISHED.
                
                AVAILABLE COMMANDS:
                ─────────────────────────────────────────────
                """);
        Map<String, Object> cmdRegistry = appContext.getCommandRegistry();
        for(Map.Entry<String, Object> cmdEntry: cmdRegistry.entrySet()) {
            System.out.println("Command(s) for " + cmdEntry.getKey());
            iterateCommandRegistry(cmdEntry.getValue());
        }
    }

    private void iterateCommandRegistry(Object cmdNode) {
        if(cmdNode == null) {
            return;
        }
        if(cmdNode instanceof Command cmd) {
            System.out.println("\t" + cmd.getName() + "\t\t" + cmd.getDescription());
        }
        if(cmdNode instanceof CommandGroup cmdGrp) {
            Map<String, CommandNode> cmdChildren = cmdGrp.getChildren();
            for(Map.Entry<String, CommandNode> cmdChildEntry: cmdChildren.entrySet()) {
                iterateCommandRegistry(cmdChildEntry.getValue());
            }
        }
    }
}
