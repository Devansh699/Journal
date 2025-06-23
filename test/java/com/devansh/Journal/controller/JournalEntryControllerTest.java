package com.devansh.Journal.controller;

import com.devansh.Journal.entity.JournalEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JournalEntryController.class)
public class JournalEntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private JournalEntry sampleEntry;

    @BeforeEach
    void setup() {
        sampleEntry = new JournalEntry();
        sampleEntry.setId(1);
        sampleEntry.setTitle("Test Title");
        sampleEntry.setContent("Test Content");
        sampleEntry.setDate(LocalDate.now());
    }

    @Test
    void testCreateEntry() throws Exception {
        mockMvc.perform(post( "/journal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleEntry)))
                .andExpect(status().isOk())
                .andExpect(content().string("Added Successfully"));
    }

    @Test
    void testGetAllEntries() throws Exception {
        // First add an entry
        mockMvc.perform(post("/journal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleEntry)))
                .andExpect(status().isOk());

        // Then get all entries
        mockMvc.perform(get("/journal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Title"));
    }

    @Test
    void testGetEntryById() throws Exception {
        mockMvc.perform(post("/journal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleEntry)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/journal/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Test Content"));
    }

    @Test
    void testUpdateEntryById() throws Exception {
        mockMvc.perform(post("/journal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleEntry)))
                .andExpect(status().isOk());

        sampleEntry.setContent("Updated Content");

        mockMvc.perform(put("/journal/id/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleEntry)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated Content"));
    }

    @Test
    void testDeleteEntryById() throws Exception {
        mockMvc.perform(post("/journal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleEntry)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/journal/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"));
    }
}
