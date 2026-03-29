package com.wpn.kanban.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int boardId;
    private String boardName;
    private Map<Task, Status> taskMap;

    public Board(int boardId, String boardName) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.taskMap = new HashMap<>(10);
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
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
}
