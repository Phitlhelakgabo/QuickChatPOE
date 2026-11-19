package com.quickchat;

public class Message {

    // Variables
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String status; // "Sent", "Stored", "Disregard"

    // Constructor (Requires 5 arguments to match Main.java)
    public Message(String messageID, int messageNumber, String recipient, String messageText, String status) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.status = status;
    }

    // --- Getters ---
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getStatus() { return status; }
    
    // The method Main.java was looking for
    public String getFlag() { return status; }

    // Helper for the delete test in ReportTest
    public String getHash() {
        return messageID + messageNumber;
    }

    // --- Validation Methods (Fixing Main.java errors) ---

    // Checks if recipient cell is valid (Starts with +27 and is 12 chars long)
    public boolean checkRecipientCell() {
        return this.recipient != null && this.recipient.startsWith("+27") && this.recipient.length() == 12;
    }

    // Checks message length (Max 250 characters)
    public String checkMessageLength() {
        if (this.messageText == null) {
            return "Message is empty.";
        }
        if (this.messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = this.messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Handles the send/disregard/store actions (1 = Send, 2 = Disregard, 3 = Store)
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                this.status = "Sent";
                return "Message successfully sent.";
            case 2:
                this.status = "Disregard";
                return "Press 0 to delete the message.";
            case 3:
                this.status = "Stored";
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    // Prints the message details nicely formatted
    public void printMessages() {
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + getHash());
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
        System.out.println("Status: " + status);
        System.out.println("-------------------------");
    }
}
