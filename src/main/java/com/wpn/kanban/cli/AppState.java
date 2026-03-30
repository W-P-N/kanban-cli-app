package com.wpn.kanban.cli;

import com.wpn.kanban.core.Board;

import java.util.ArrayList;
import java.util.List;

public class AppState {
    private boolean running = true;
    public void stop() {
        this.running = false;
    }
    private final List<Board> boardList = new ArrayList<>();
    public boolean isRunning() {
        return running;
    }
    public void addBoard(Board board) {
        for(Board listBoard: boardList) {
            if(listBoard.equals(board)) {
                System.out.println("Board already exists");
                return;
            }
        }
        boardList.add(board);
    }
    public List<Board> getBoardList() {
        return new ArrayList<>(boardList);
    }
}
