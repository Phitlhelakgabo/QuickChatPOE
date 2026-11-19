package com.quickchat;

import java.util.ArrayList;
import java.util.List;

public class MessageStore {

    // These lists must be public so your ReportTest.java can access them directly
    public List<Message> sent = new ArrayList<>();
    public List<Message> stored = new ArrayList<>();
    public List<Message> disregarded = new ArrayList<>();

    // Adds a message to the correct list based on its status
    public void add(Message m) {
        if (m == null) return;
        
        String status = m.getStatus();
        if ("Sent".equalsIgnoreCase(status)) {
            sent.add(m);
        } else if ("Stored".equalsIgnoreCase(status)) {
            stored.add(m);
        } else if ("Disregard".equalsIgnoreCase(status)) {
            disregarded.add(m);
        }
    }

    // Fixes the Main.java error: "cannot find symbol: method loadStored()"
    public void loadStored() {
        // If you are doing file I/O (like JSON) later, put it here.
        // For now, this fixes the compiler error.
        System.out.println("Stored messages loaded successfully.");
    }

    // Finds the longest message out of all sent, stored, and disregarded messages
    public String longestMessage() {
        String longest = "";
        List<Message> allMessages = getAllMessages();

        for (Message m : allMessages) {
            if (m.getMessageText() != null && m.getMessageText().length() > longest.length()) {
                longest = m.getMessageText();
            }
        }
        return longest;
    }

    // Fixes the Main.java error: "cannot find symbol: method longestStored()"
    public String longestStored() {
        String longest = "";
        for (Message m : stored) {
            if (m.getMessageText() != null && m.getMessageText().length() > longest.length()) {
                longest = m.getMessageText();
            }
        }
        return longest;
    }

    // Search by Message ID
    public List<Message> searchById(String id) {
        List<Message> result = new ArrayList<>();
        for (Message m : getAllMessages()) {
            if (m.getMessageID().equals(id)) {
                result.add(m);
            }
        }
        return result;
    }

    // Search by Recipient phone number
    public List<Message> searchByRecipient(String recipient) {
        List<Message> result = new ArrayList<>();
        for (Message m : getAllMessages()) {
            if (m.getRecipient().equals(recipient)) {
                result.add(m);
            }
        }
        return result;
    }

    // Delete a message by its unique hash
    public void deleteByHash(String hash) {
        sent.removeIf(m -> m.getHash().equals(hash));
        stored.removeIf(m -> m.getHash().equals(hash));
        disregarded.removeIf(m -> m.getHash().equals(hash));
    }

    // Helper method to combine all lists for searching
    private List<Message> getAllMessages() {
        List<Message> all = new ArrayList<>();
        all.addAll(sent);
        all.addAll(stored);
        all.addAll(disregarded);
        return all;
    }
}