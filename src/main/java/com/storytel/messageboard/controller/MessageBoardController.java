package com.storytel.messageboard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.storytel.messageboard.exception.MessageNotFoundException;
import com.storytel.messageboard.model.Message;
import com.storytel.messageboard.repository.MessageRepository;

@RestController
public class MessageBoardController {
    private final MessageRepository repository;

    public MessageBoardController(MessageRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin
    @GetMapping("/message")
    public ResponseEntity<Object> getAllMessages() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/message/{id}")
    public ResponseEntity<Object> getOneMessage(@PathVariable Integer id) {
        return new ResponseEntity<>(repository.findById(id).orElseThrow(() -> new MessageNotFoundException(id)),
                HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/message")
    public ResponseEntity<Object> createMessage(@RequestBody Message message) {
        return new ResponseEntity<>(repository.save(message), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping("/message/{id}")
    public ResponseEntity<Object> updateMessage(@PathVariable("id") Integer id, @RequestBody Message newMessage) {
        return new ResponseEntity<>(repository.findById(id)
                .map(message -> {
                    message.setAuthor(newMessage.getAuthor());
                    message.setContent(newMessage.getContent());
                    return repository.save(message);
                })
                .orElseGet(() -> {
                    newMessage.setId(id);
                    return repository.save(newMessage);
                }), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/message/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable("id") Integer id) {
        repository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        repository.deleteById(id);
        return new ResponseEntity<>("Message deleted!", HttpStatus.OK);
    }
}
