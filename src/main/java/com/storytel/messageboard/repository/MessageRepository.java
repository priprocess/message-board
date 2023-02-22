package com.storytel.messageboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storytel.messageboard.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
