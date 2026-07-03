package com.wpn.kanban.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Board {
    private final String boardId;
    private String boardName;
    private final Map<String, Task> taskMap;

    @JsonCreator
    public Board(
            @JsonProperty("boardId") String boardId,
            @JsonProperty("boardName") String boardName,
            @JsonProperty("taskMap") Map<String, Task> taskMap
    ) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.taskMap = taskMap != null ? taskMap : new HashMap<>(10);
    }

    public Map<String, Task> getTaskMap() {
        return taskMap;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public boolean addTask(String taskName, String taskDescription) {
        for(Map.Entry<String,Task> entry: taskMap.entrySet()) {
            if(entry.getValue().getTitle().equals(taskName)) {
                return false;
            }
        }
        Random randomNum = new Random();
        String taskId = "" + randomNum.nextInt(10000);
        Task task = new Task(taskId, taskName);
        if(taskDescription != null) {
            task.setDescription(taskDescription);
        }
        taskMap.put(taskId, task);
        return true;
    }

    public Task findTask(String taskId) {
        for(Map.Entry<String, Task> entry: taskMap.entrySet()) {
            if(entry.getKey().equals(taskId)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean equals(Board board) {
        return (!Objects.equals(this.getBoardId(), board.getBoardId())) && (this.getBoardName().equals(board.getBoardName()));
    }

    public void viewBoard() {
        List<Task> todoTasks = new ArrayList<>();
        List<Task> doingTasks = new ArrayList<>();
        List<Task> finishedTasks = new ArrayList<>();

        for(Map.Entry<String, Task> entry: taskMap.entrySet()) {
            Task currentTask = entry.getValue();
            Status currentTaskStatus = currentTask.getStatus();
            if(currentTaskStatus.equals(Status.TODO)) {
                todoTasks.add(currentTask);
            } else if(currentTaskStatus.equals(Status.DOING)) {
                doingTasks.add(currentTask);
            } else {
                finishedTasks.add(currentTask);
            }
        }

        int totalTasks = todoTasks.size() + doingTasks.size() + finishedTasks.size();
        double completionPercentage = totalTasks == 0 ? 0 : (double) finishedTasks.size() / totalTasks * 100;
        int progressBarFilled = (int) (completionPercentage / 100 * 20);
        String progressBar = "█".repeat(progressBarFilled) + "░".repeat(20 - progressBarFilled);
        String health = completionPercentage >= 70 ? "🟢" : completionPercentage >= 30 ? "🟡" : "🔴";

        System.out.println("┌──────────────────────────────────────────────────────┐");
        System.out.printf( "│  %-30s     HEALTH: %s %.0f%%   │%n", boardName, health, completionPercentage);
        System.out.println("│  ──────────────────────────────────────────────────  │");
        System.out.printf( "│  Progress: %s  %d/%d done            │%n", progressBar, finishedTasks.size(), totalTasks);
        System.out.println("├───────────┬──────────────────────────────────────────┤");

        printTaskSection("TODO", todoTasks);
        System.out.println("├───────────┼──────────────────────────────────────────┤");
        printTaskSection("DOING", doingTasks);
        System.out.println("├───────────┼──────────────────────────────────────────┤");
        printTaskSection("FINISHED", finishedTasks);

        System.out.println("└───────────┴──────────────────────────────────────────┘");
    }

    private void printTaskSection(String label, List<Task> tasks) {
        if(tasks.isEmpty()) {
            System.out.printf("│  %-8s │  %-40s│%n", label, "-");
            return;
        }
        for(int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String sectionLabel = i == 0 ? label : "";
            System.out.printf("│  %-8s │  #%-6s %-33s│%n",
                    sectionLabel,
                    task.getId(),
                    task.getTitle());
        }
    }

    public boolean deleteTask(String taskId) {
        Task removedTask = taskMap.remove(taskId);
        return removedTask != null;
    }

    public boolean renameTask(String taskId, String newName) {
        Task foundTask = findTask(taskId);
        if(foundTask == null) {
            return false;
        }
        foundTask.setTitle(newName);
        return true;
    }

    public Map<Status, Integer> countTaskByStatus() {
        Map<Status, Integer> taskCountByStatus = new HashMap<>();
        taskCountByStatus.put(Status.TODO, 0);
        taskCountByStatus.put(Status.DOING, 0);
        taskCountByStatus.put(Status.FINISHED, 0);
        for(Map.Entry<String, Task> entry: taskMap.entrySet()) {
            Task currentTask = entry.getValue();
            Status currentTaskStatus = currentTask.getStatus();
            taskCountByStatus.put(currentTaskStatus, taskCountByStatus.get(currentTaskStatus) + 1);
        }
        return taskCountByStatus;
    }


}
