package com.wpn.kanban.cli;

import com.wpn.kanban.core.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AppState {
    private boolean running = true;
    public void stop() {
        this.running = false;
    }
    private final List<Board> boardList = new ArrayList<>();
    private final AtomicInteger boardIdCounter = new AtomicInteger(1000);
    public boolean isRunning() {
        return running;
    }
    public boolean addBoard(Board board) {
        for(Board listBoard: boardList) {
            if(listBoard.equals(board)) {
                return false;
            }
        }
        boardList.add(board);
        return true;
    }
    public List<Board> getBoardList() {
        return new ArrayList<>(boardList);
    }
    public void resetBoardIdCounter() {
        boardIdCounter.set(1000);
    }
    public int getBoardIdFromCounter() {
        return boardIdCounter.incrementAndGet();
    }
}
