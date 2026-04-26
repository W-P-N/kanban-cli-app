package com.wpn.kanban.core;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Board {
    private int boardId;
    private String boardName;
    private Map<String, Task> taskMap;
    private AtomicInteger taskIdCounter = new AtomicInteger(1000);

    public Board(int boardId, String boardName) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.taskMap = new HashMap<>(10);
    }

    public int getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void addTask(String taskName, String taskDescription) {
        String taskId = "" + taskIdCounter.incrementAndGet();
        Task task = new Task(taskId, taskName);
        if(taskDescription != null) {
            task.setDescription(taskDescription);
        }
        taskMap.put(taskId, task);
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
        return (this.getBoardId() != board.getBoardId()) && (this.getBoardName().equals(board.getBoardName()));
    }

    public void listTask() {
        for(Map.Entry<String, Task> entry: taskMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getTitle());
        }
    }

    public void viewBoard() {
        List<Task> todoTasks = new ArrayList<>();
        List<Task> doingTasks = new ArrayList<>();
        List<Task> finishedTasks = new ArrayList<>();

        for(Map.Entry<String, Task> entry: taskMap.entrySet()) {
            Task currentTask = entry.getValue();
            switch(currentTask.getStatus()) {
                case Status.TODO:
                    todoTasks.add(currentTask);
                    break;
                case Status.DOING:
                    doingTasks.add(currentTask);
                    break;
                case Status.FINISHED:
                    finishedTasks.add(currentTask);
                    break;
                default:
                    break;
            }
        }

        System.out.println("TODO: ");
        for(Task task: todoTasks) {
            System.out.print(task.getId() + ": " + task.getTitle());
        }
        System.out.println();
        System.out.println("DOING: ");
        for(Task task: doingTasks) {
            System.out.print(task.getId() + ": " + task.getTitle());
        }
        System.out.println();
        System.out.println("FINISHED: ");
        for(Task task: finishedTasks) {
            System.out.print(task.getId() + ": " + task.getTitle());
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


}
