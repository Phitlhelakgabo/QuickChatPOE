
package com.quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageIDGenerated() {
        Message message = new Message(
                "+27834557896", "Hello", 1);

        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testValidRecipientCell() {
        Message message = new Message(
                "+27834557896", "Hello", 1);

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipientCell() {
        Message message = new Message(
                "0838884567", "Hello", 1);

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code.",
                message.checkRecipientCell());
    }

    @Test
    public void testValidMessageLength() {
        Message message = new Message(
                "+27834557896", "Hello there", 1);

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength());
    }

    @Test
    public void testInvalidMessageLength() {
        String longMessage = "A".repeat(251);

        Message message = new Message(
                "+27834557896", longMessage, 1);

        assertEquals(
                "Message exceeds 250 characters.",
                message.checkMessageLength());
    }

    @Test
    public void testMessageHashCreated() {
        Message message = new Message(
                "+27834557896", "Hello there", 1);

        assertNotNull(message.getMessageHash());
        assertFalse(message.getMessageHash().isEmpty());
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "+27834557896", "Hello", 1);

        assertEquals(
                "Message successfully sent.",
                message.SentMessage("1"));
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "+27834557896", "Hello", 1);

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage("2"));
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "+27834557896", "Hello", 1);

        assertEquals(
                "Message successfully stored.",
                message.SentMessage("3"));
    }

    @Test
    public void testTotalMessagesSent() {
        Message message = new Message(
                "+27834557896", "Hello", 1);

        int before = message.returnTotalMessagess();

        message.SentMessage("1");

        assertEquals(
                before + 1,
                message.returnTotalMessagess());
    }

    // PART 3 TESTS

    @Test
    public void testSentMessagesArray() {
        MessageStore store = new MessageStore();

        assertNotNull(store.getSentMessages());
    }

    @Test
    public void testDisregardedMessagesArray() {
        MessageStore store = new MessageStore();

        assertNotNull(store.getDisregardedMessages());
    }

    @Test
    public void testStoredMessagesArray() {
        MessageStore store = new MessageStore();

        assertNotNull(store.getStoredMessages());
    }

    @Test
    public void testMessageHashesArray() {
        MessageStore store = new MessageStore();

        assertNotNull(store.getMessageHashes());
    }

    @Test
    public void testMessageIDsArray() {
        MessageStore store = new MessageStore();

        assertNotNull(store.getMessageIDs());
    }

    @Test
    public void testSearchMessageByID() {
        MessageStore store = new MessageStore();

        String[] ids = store.getMessageIDs();

        if (ids.length > 0) {
            String result = store.searchMessageByID(ids[0]);

            assertTrue(result.contains("Recipient:"));
            assertTrue(result.contains("Message:"));
        }
    }

    @Test
    public void testDeleteMessageByHash() {
        MessageStore store = new MessageStore();

        String[] hashes = store.getMessageHashes();

        if (hashes.length > 0) {
            String hash = hashes[0];

            assertTrue(store.deleteMessageByHash(hash));
            assertFalse(store.deleteMessageByHash(hash));
        }
    }

    @Test
    public void testDisplayStoredMessagesReport() {
        MessageStore store = new MessageStore();

        String report = store.displayStoredMessagesReport();

        assertNotNull(report);
    }

    @Test
    public void testSearchMessagesByRecipient() {
        MessageStore store = new MessageStore();

        String result = store.searchMessagesByRecipient(
                "+27838884567");

        assertNotNull(result);
    }

    @Test
    public void testDisplayLongestStoredMessage() {
        MessageStore store = new MessageStore();

        String result = store.displayLongestStoredMessage();

        assertNotNull(result);
    }
}
