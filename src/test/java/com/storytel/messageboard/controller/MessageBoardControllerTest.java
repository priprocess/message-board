package com.storytel.messageboard.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.storytel.messageboard.AbstractTest;
import com.storytel.messageboard.model.Message;

public class MessageBoardControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.populateRepositories();
        super.setUp();
    }

    @Test
    public void getAllMessages() throws Exception {
        String uri = "/message";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

        String content = mvcResult.getResponse().getContentAsString();
        Message[] messages = super.mapFromJson(content, Message[].class);
        assertTrue(messages.length == 2);
    }

    @Test
    public void createMessage() throws Exception {
        String uri = "/message";
        Message message = new Message("Elon", "Check my Rocket!");

        String requestJson = super.mapToJson(message);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andReturn();
        assertEquals(HttpStatus.CREATED.value(),
                mvcResult.getResponse().getStatus());

        Message responseMessage = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Message.class);
        assertEquals(message, responseMessage);
    }

    @Test
    public void updateMessage() throws Exception {
        String uri = "/message/1";
        Message message = new Message();
        message.setAuthor("Jasper");
        message.setContent("We have lost contact with the device.");

        String requestJson = super.mapToJson(message);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson)).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

        Message responseMessage = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Message.class);
        assertEquals(message, responseMessage);
    }

    @Test
    public void deleteMessage() throws Exception {
        String uri = "/message/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

        String actualContent = mvcResult.getResponse().getContentAsString();
        String expectedContent = "Message deleted!";

        assertEquals(expectedContent, actualContent);
    }
}
