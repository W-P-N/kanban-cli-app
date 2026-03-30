package com.wpn.kanban.exceptions;

public class PartialCommandException extends Exception{
    public PartialCommandException(String message) {
        super(message);
    }
}
