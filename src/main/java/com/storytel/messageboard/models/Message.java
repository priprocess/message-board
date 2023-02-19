package com.storytel.messageboard.models;


import java.util.Objects;

public class Message {
    private int id;
    private String author;
    private String messageContent;

    public Message() {}

    public Message(int id, String author, String message) {
        this.id = id;
        this.author = author;
        this.messageContent = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String message) {
        this.messageContent = message;
    }

    // public Timestamp getCreationTime() {
    //     return creationTime;
    // }

    // public void setCreationTime(Timestamp creationTime) {
    //     this.creationTime = creationTime;
    // }

    // public Timestamp getLastEditTime() {
    //     return lastEditTime;
    // }

    // public void setModificationTime(Timestamp editTimestamp) {
    //     this.lastEditTime = editTimestamp;
    // }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Message))
            return false;
        Message message = (Message) o;
        /* Uniqueness Implications: Agree on uniqueness constraints if any, as that translates into equality impl */
        return Objects.equals(this.id, message.id) && Objects.equals(this.messageContent, message.messageContent) 
            && Objects.equals(this.author, message.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.messageContent, this.author);
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + this.id + ", author='" + this.author + '\'' + ", message='" + this.messageContent + '\'' + "}";
    }
}