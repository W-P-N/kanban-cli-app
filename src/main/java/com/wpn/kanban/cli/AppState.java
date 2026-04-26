package com.wpn.kanban.cli;

import com.wpn.kanban.core.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class AppState {
    private Board activeBoard;
    private final List<Board> boardList = new ArrayList<>();
    private final AtomicInteger boardIdCounter = new AtomicInteger(1000);

    public boolean addBoard(String boardName) {
        for(Board board: boardList) {
            if(board.getBoardName().equals(boardName)) {
                return false;
            }
        }
        String boardId = "" + boardIdCounter.incrementAndGet();
        Board newBoard = new Board(boardId, boardName);
        boardList.add(newBoard);
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

    public boolean deleteBoard(String boardId) {
        boolean isBoardRemoved = false;
        isBoardRemoved = boardList.removeIf(board -> board.getBoardId().equals(boardId));
        return isBoardRemoved;
    }

    public boolean renameBoard(String boardId, String boardName) {
        boolean containsBoard = false;
        for(Board board: boardList) {
            if (board.getBoardId().equals(boardId)) {
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

    public boolean setBoardActive(String boardId) {
        boolean containsBoard = false;
        for(Board board: boardList) {
            if (board.getBoardId().equals(boardId)) {
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
