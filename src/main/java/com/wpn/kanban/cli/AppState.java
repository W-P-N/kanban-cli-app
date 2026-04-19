package com.wpn.kanban.cli;

import com.wpn.kanban.core.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class AppState {
    private Board activeBoard;
    private final List<Board> boardList = new ArrayList<>();
    private final AtomicInteger boardIdCounter = new AtomicInteger(1000);
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
    public boolean deleteBoard(int boardId) {
        boolean isBoardRemoved = false;
        isBoardRemoved = boardList.removeIf(board -> board.getBoardId() == boardId);
        return isBoardRemoved;
    };
    public boolean renameBoard(int boardId, String boardName) {
        boolean containsBoard = false;
        for(Board board: boardList) {
            if (board.getBoardId() == boardId) {
                containsBoard = true;
                board.setBoardName(boardName);
                break;
            }
        }
        return containsBoard;
    }
    private void setActiveBoard(Board board) {
        this.activeBoard = board;
    }
    public Board getActiveBoard() {
        return this.activeBoard;
    }
    public boolean setBoardActive(int boardId) {
        boolean containsBoard = false;
        for(Board board: boardList) {
            if (board.getBoardId() == boardId) {
                containsBoard = true;
                setActiveBoard(board);
                break;
            }
        }
        return containsBoard;
    }
    public boolean closeActiveBoard() {
        if(this.activeBoard == null) {
            return false;
        }
        this.activeBoard = null;
        return true;
    }
}
