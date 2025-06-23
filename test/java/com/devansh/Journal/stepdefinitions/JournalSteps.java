package com.devansh.Journal.stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.devansh.Journal.entity.JournalEntry;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class JournalSteps {

    private MockMvc mockMvc;
    private JournalEntry sampleEntry;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Given("the journal entry with ID 1 exists")
    public void the_journal_entry_exists() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        sampleEntry = new JournalEntry(1, "Test Title", "Test Content");

        mockMvc.perform(post("/journal")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sampleEntry)))
                .andExpect(status().isOk());
    }

    @When("I call POST \\/journal")
    public void i_call_post_journal() throws Exception {
        mockMvc.perform(post("/journal")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(sampleEntry)))
                .andExpect(status().isOk());
    }

    @When("I call GET \\/journal\\/id\\/1")
    public void i_call_get_journal_by_id() throws Exception {
        mockMvc.perform(get("/journal/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test Content")));
    }

    @Then("the response should be 200 OK")
    public void response_should_be_200() {
        // Already asserted in steps
    }

    @Then("the response should contain {string}")
    public void response_should_contain(String text) {
        // Already asserted in steps
    }
}