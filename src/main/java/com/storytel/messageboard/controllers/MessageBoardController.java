package com.storytel.messageboard.controllers;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.storytel.messageboard.exceptions.MessageNotFoundException;
import com.storytel.messageboard.models.Message;


@RestController
public class MessageBoardController {

    private static final AtomicInteger messageCounter = new AtomicInteger(0); 
    private static Map<Integer, Message> messageRepo = new HashMap<>();

    @GetMapping("/message")
    public ResponseEntity<Object> getAllMessages() {
        return new ResponseEntity<>(messageRepo.values(), HttpStatus.OK);
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Object> getOneMessage(@PathVariable int id) {
        if(!messageRepo.containsKey(id))
            throw new MessageNotFoundException(id);
        return new ResponseEntity<>(messageRepo.get(id), HttpStatus.OK);
    }

    @PostMapping("/message")
    public ResponseEntity<Object> createMessage(@RequestBody Message message) {
        message.setId(messageCounter.getAndIncrement());
        messageRepo.put(message.getId(), message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Object> updateMessage(@PathVariable("id") int id, @RequestBody Message message) {
        if(!messageRepo.containsKey(id))
            throw new MessageNotFoundException(id);
        messageRepo.remove(id);
        message.setId(id);
        messageRepo.put(id, message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable("id") int id){
        messageRepo.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
