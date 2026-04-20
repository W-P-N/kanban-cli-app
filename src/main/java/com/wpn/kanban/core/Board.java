package com.wpn.kanban.core;

import java.util.*;

public class Board {
    private int boardId;
    private String boardName;
    private Map<Integer, Task> taskMap;

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

    public boolean equals(Board board) {
        return (this.getBoardId() != board.getBoardId()) && (this.getBoardName().equals(board.getBoardName()));
    }
}
