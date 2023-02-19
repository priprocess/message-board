package com.storytel.messageboard.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.storytel.messageboard.AbstractTest;
import com.storytel.messageboard.models.Message;

public class MessageBoardControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAllMessages() throws Exception {
        String uri = "/message";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        // String content = mvcResult.getResponse().getContentAsString();
        // Message[] messages = super.mapFromJson(content, Message[].class);
        // assertTrue(messages.length > 0);
    }

    @Test
    public void createMessage() throws Exception {
        String uri = "/message";
        Message message = new Message();
        message.setAuthor("Elon");
        message.setMessageContent("I have a new message about the rocket. It has launched.");

        String requestJson = super.mapToJson(message);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andReturn();
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());

        String actualContent = mvcResult.getResponse().getContentAsString();
        assertEquals(requestJson, actualContent);
    }

    @Test
    public void updateMessage() throws Exception {
        String uri = "/message/1";
        Message message = new Message();
        message.setAuthor("Jasper");
        message.setMessageContent("We have lost contact with the device.");

        String requestJson = super.mapToJson(message);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        String actualContent = mvcResult.getResponse().getContentAsString();
        String expectedContent = "Message updated!";

        assertEquals(expectedContent, actualContent);
    }

    // @Test
    // public void deleteMessage() throws Exception {
    // String uri = "/message/1";

    // MvcResult mvcResult =
    // mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();

    // assertEquals(200, mvcResult.getResponse().getStatus());

    // String actualContent = mvcResult.getResponse().getContentAsString();
    // String expectedContent = "Message deleted!";

    // assertEquals(expectedContent, actualContent);
    // }

}
