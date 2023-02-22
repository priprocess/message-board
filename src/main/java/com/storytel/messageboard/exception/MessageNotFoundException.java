package com.storytel.messageboard.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(int id) {
        super("Could not find message with id: "+id);
    }
}
