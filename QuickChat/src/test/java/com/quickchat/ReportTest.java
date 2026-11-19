package com.quickchat;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void testReportFeatures() throws Exception {
        MessageStore s = new MessageStore();

        // Populating test data
        // Note: Make sure the 5th argument matches your Message constructor (likely the text)
        Message m1 = new Message("0838884567", 1, "+27834557896", "Did you get the cake?", "Sent");
        Message m2 = new Message("0838884568", 2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        Message m3 = new Message("0838884569", 3, "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        Message m4 = new Message("0838884570", 4, "+27834557896", "It is dinner time !", "Sent");
        Message m5 = new Message("0838884571", 5, "+27838884567", "Ok, I am leaving without you.", "Stored");

        s.add(m1); s.add(m2); s.add(m3); s.add(m4); s.add(m5);

        // Test Arrays
        assertEquals(2, s.sent.size());
        assertEquals(2, s.stored.size());
        assertEquals(1, s.disregarded.size());

        // Test Longest
        assertEquals("Where are you? You are late! I have asked you to be on time.", s.longestMessage());

        // Test Search by ID
        List<Message> searchByIdResult = s.searchById("0838884570");
        assertFalse(searchByIdResult.isEmpty(), "Search by ID returned empty list");
        assertEquals("It is dinner time !", searchByIdResult.get(0).getMessageText());

        // Test Search by Recipient
        List<Message> searchByRecipientResult = s.searchByRecipient("+27838884567");
        assertEquals(2, searchByRecipientResult.size(), "Recipient search should find 2 messages");

        // Test Delete
        s.deleteByHash(m2.getHash());
        assertEquals(1, s.stored.size(), "Stored size should be 1 after deletion");
    }
}