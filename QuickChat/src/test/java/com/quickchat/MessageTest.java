package com.quickchat;

import com.quickchat.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    @Test void testMessageIdLength() {
        Message m = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(m.checkMessageID());
    }

    @Test void testRecipient() {
        Message m = new Message("+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
        Message bad = new Message("08575975889", "Hello");
        assertTrue(bad.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test void testLength() {
        Message m = new Message("+27718693002", "x".repeat(260));
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", m.checkMessageLength());
    }

    @Test void testHash() {
        Message m = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }

    @Test void testSendStoreDiscard() {
        Message m = new Message("+27718693002", "Hello");
        assertEquals("Message successfully sent.", m.sentMessage(1));
        assertEquals("Press 0 to delete the message.", m.sentMessage(2));
        assertEquals("Message successfully stored.", m.sentMessage(3));
    }
}