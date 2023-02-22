package com.storytel.messageboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Message {
    private @Id @GeneratedValue Integer id;
    private String author;
    private String content;

    public Message() {
    }

    public Message(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String message) {
        this.content = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Message))
            return false;
        Message message = (Message) o;
        return Objects.equals(this.content, message.content)
                && Objects.equals(this.author, message.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.author, this.content);
    }

    @Override
    public String toString() {
        return "Message{"
                + "id=" + this.id
                + ", author='" + this.author + '\''
                + ", message='" + this.content + '\''
                + "}";
    }
}
