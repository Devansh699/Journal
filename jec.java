package com.devansh.Journal.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

public class JournalEntry {
    private long id;
    private String title;
    private String content;
    private LocalDate date;

    public JournalEntry(int i, String testTitle, String testContent) {
    }

    public JournalEntry() {

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
