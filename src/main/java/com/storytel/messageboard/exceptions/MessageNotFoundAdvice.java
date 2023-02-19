package com.storytel.messageboard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessageNotFoundAdvice {

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Object> exception(MessageNotFoundException exception) {
        return new ResponseEntity<>("Message not found!", HttpStatus.NOT_FOUND);
    }
}
