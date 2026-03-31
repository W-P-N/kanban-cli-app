package com.wpn.kanban.core;

import java.util.*;

public class Board {
    private UUID boardId;
    private String boardName;
    private Map<Task, Status> taskMap;

    public Board(UUID boardId, String boardName) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.taskMap = new HashMap<>(10);
    }

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public List<Task> getTaskListByStatus(Status reqStatus) {
        List<Task> taskList = new ArrayList<>(10);
        for(Map.Entry<Task, Status> entry: taskMap.entrySet()) {
            Status st = entry.getValue();
            if(st.equals(reqStatus)) {
                taskList.add(entry.getKey());
            }
        }
        return taskList;
    }

    public boolean equals(Board board) {
        return (this.getBoardId() != board.getBoardId()) && (this.getBoardName().equals(board.getBoardName()));
    }
}
