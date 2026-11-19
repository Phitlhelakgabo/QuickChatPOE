
package com.quickchat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageID;
    private String recipient;
    private String messageText;
    private int messageNumber;
    private String messageHash;
    private String messageStatus;

    private static int totalMessagesSent = 0;

    private static final String FILE_NAME = "messages.json";

    // Constructor
    public Message(String recipient, String messageText, int messageNumber) {

        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageStatus = "Pending";

        this.messageHash = createMessageHash();
    }

    // Generate a random 10-digit message ID
    private String generateMessageID() {

        Random random = new Random();

        long number = 1000000000L
                + (long) (random.nextDouble() * 9000000000L);

        return String.valueOf(number);
    }

    // Check message ID
    public boolean checkMessageID() {

        return messageID != null
                && messageID.matches("[0-9]{10}");
    }

    // Validate recipient cellphone number
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.matches("^\\+27[0-9]{9}$")) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
    }

    // Validate message length
    public String checkMessageLength() {

        if (messageText == null || messageText.trim().isEmpty()) {

            return "Message cannot be empty.";
        }

        if (messageText.length() <= 250) {

            return "Message ready to send.";

        } else {

            return "Message exceeds 250 characters.";
        }
    }

    // Create message hash
    public String createMessageHash() {

        String firstTwoDigits = messageID.substring(0, 2);

        String cleanMessage = messageText
                .replaceAll("[^a-zA-Z0-9\\s]", "")
                .trim();

        if (cleanMessage.isEmpty()) {

            return firstTwoDigits + ":" + messageNumber + ":";
        }

        String[] words = cleanMessage.split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (firstTwoDigits + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // Send, disregard or store message
    public String SentMessage(String choice) {

        switch (choice) {

            case "1":

                messageStatus = "Sent";
                totalMessagesSent++;

                return "Message successfully sent.";

            case "2":

                messageStatus = "Disregarded";

                return "Press 0 to delete the message.";

            case "3":

                if (storeMessage()) {

                    messageStatus = "Stored";

                    return "Message successfully stored.";

                } else {

                    return "Message could not be stored.";
                }

            default:

                return "Invalid message option.";
        }
    }

    // Save messages to JSON file
    public boolean storeMessage() {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        JsonArray messages = new JsonArray();

        File file = new File(FILE_NAME);

        try {

            // Read existing JSON messages
            if (file.exists() && file.length() > 0) {

                try (FileReader reader = new FileReader(file)) {

                    messages = JsonParser.parseReader(reader)
                            .getAsJsonArray();
                }
            }

            // Create JSON message
            JsonObject messageObject = new JsonObject();

            messageObject.addProperty("Message ID", messageID);
            messageObject.addProperty("Message Hash", messageHash);
            messageObject.addProperty("Recipient", recipient);
            messageObject.addProperty("Message", messageText);
            messageObject.addProperty("Message Number", messageNumber);
            messageObject.addProperty("Status", "Stored");

            messages.add(messageObject);

            // Save JSON file in project folder
            try (FileWriter writer = new FileWriter(file)) {

                gson.toJson(messages, writer);
            }

            System.out.println("JSON file saved successfully!");
            System.out.println("File location: "
                    + file.getAbsolutePath());

            return true;

        } catch (IOException | RuntimeException e) {

            System.out.println("JSON storage error: " + e.getMessage());

            return false;
        }
    }

    // Print message details
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    // Return total messages sent
    public int returnTotalMessagess() {

        return totalMessagesSent;
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageStatus() {
        return messageStatus;
    }
}
