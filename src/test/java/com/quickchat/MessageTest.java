
package com.quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageIDGenerated() {
        Message message = new Message(
                "+27718693002",
                "Hello Mike",
                0
        );

        assertNotNull(message.getMessageID());
        assertEquals(10, message.getMessageID().length());
        assertTrue(message.checkMessageID());
    }

    @Test
    public void testValidRecipientCell() {
        Message message = new Message(
                "+27718693002",
                "Hello Mike",
                0
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testInvalidRecipientCell() {
        Message message = new Message(
                "08575975889",
                "Hello Mike",
                0
        );

        assertTrue(
                message.checkRecipientCell().toLowerCase().contains("incorrect")
        );
    }

    @Test
    public void testValidMessageLength() {
        Message message = new Message(
                "+27718693002",
                "Hello Mike",
                0
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testInvalidMessageLength() {
        String longMessage = "A".repeat(251);

        Message message = new Message(
                "+27718693002",
                longMessage,
                0
        );

        assertTrue(
                message.checkMessageLength().contains("exceeds 250 characters")
        );
    }

    @Test
    public void testMessageHashCreated() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        String hash = message.createMessageHash();

        // First two digits of ID, message number, first and last words.
        assertTrue(hash.matches("\\d{2}:0:HITONIGHT"));
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "+27718693002",
                "Hello Mike",
                0
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage("1")
        );
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "+27718693002",
                "Hello Mike",
                0
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage("2")
        );
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "+27718693002",
                "Hello Mike",
                0
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage("3")
        );
    }

    @Test
    public void testTotalMessagesSent() {
        Message message = new Message(
                "+27718693002",
                "Hello Mike",
                0
        );

        int totalBefore = message.returnTotalMessagess();

        message.SentMessage("1");

        int totalAfter = message.returnTotalMessagess();

        assertEquals(totalBefore + 1, totalAfter);
    }
}
