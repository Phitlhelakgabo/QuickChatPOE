package com.quickchat;

import java.util.List;

public class Report {

    private MessageStore messageStore;

    // Constructor that accepts the MessageStore
    public Report(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    // Default constructor
    public Report() {
        this.messageStore = new MessageStore();
    }

    // Generates a clean text report of all messages
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        
        report.append("=== QUICKCHAT REPORT ===\n\n");
        
        report.append("SENT MESSAGES (").append(messageStore.sent.size()).append("):\n");
        appendMessageList(report, messageStore.sent);
        
        report.append("\nSTORED MESSAGES (").append(messageStore.stored.size()).append("):\n");
        appendMessageList(report, messageStore.stored);
        
        report.append("\nDISREGARDED MESSAGES (").append(messageStore.disregarded.size()).append("):\n");
        appendMessageList(report, messageStore.disregarded);
        
        report.append("\n========================\n");
        
        return report.toString();
    }

    // Helper method to format each message
    private void appendMessageList(StringBuilder report, List<Message> messages) {
        if (messages.isEmpty()) {
            report.append("  No messages.\n");
        } else {
            for (Message msg : messages) {
                report.append("  [").append(msg.getId()).append("] ")
                      .append("To: ").append(msg.getRecipient())
                      .append(" | ").append(msg.getMessageText())
                      .append("\n");
            }
        }
    }
}