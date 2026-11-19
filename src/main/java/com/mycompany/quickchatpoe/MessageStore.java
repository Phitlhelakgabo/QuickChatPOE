
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
import java.util.ArrayList;
import java.util.List;

public class MessageStore {

    private String[] sentMessages;
    private String[] disregardedMessages;
    private String[] storedMessages;
    private String[] messageHashes;
    private String[] messageIDs;

    private final List<JsonObject> allMessages;
    private final Gson gson;
    private final File jsonFile;

    public MessageStore() {
        sentMessages = new String[0];
        disregardedMessages = new String[0];
        storedMessages = new String[0];
        messageHashes = new String[0];
        messageIDs = new String[0];

        allMessages = new ArrayList<>();
        gson = new GsonBuilder().setPrettyPrinting().create();
        jsonFile = new File("messages.json");

        loadMessages();
    }

    // Load messages from the JSON file
    public void loadMessages() {
        allMessages.clear();

        if (!jsonFile.exists()) {
            updateArrays();
            return;
        }

        try (FileReader reader = new FileReader(jsonFile)) {
            JsonArray jsonArray = JsonParser.parseReader(reader)
                    .getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                allMessages.add(jsonArray.get(i).getAsJsonObject());
            }

        } catch (IOException | IllegalStateException e) {
            System.out.println("Error loading messages: " + e.getMessage());
        }

        updateArrays();
    }

    // Populate all arrays using the messages in the JSON file
    private void updateArrays() {
        List<String> sent = new ArrayList<>();
        List<String> disregarded = new ArrayList<>();
        List<String> stored = new ArrayList<>();
        List<String> hashes = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        for (JsonObject message : allMessages) {
            String text = getValue(message, "Message");
            String status = getValue(message, "Status");

            if (status.equalsIgnoreCase("Sent")) {
                sent.add(text);
            } else if (status.equalsIgnoreCase("Disregarded")) {
                disregarded.add(text);
            } else if (status.equalsIgnoreCase("Stored")) {
                stored.add(text);
            }

            hashes.add(getValue(message, "Message Hash"));
            ids.add(getValue(message, "Message ID"));
        }

        sentMessages = sent.toArray(new String[0]);
        disregardedMessages = disregarded.toArray(new String[0]);
        storedMessages = stored.toArray(new String[0]);
        messageHashes = hashes.toArray(new String[0]);
        messageIDs = ids.toArray(new String[0]);
    }

    private String getValue(JsonObject message, String key) {
        if (message.has(key) && !message.get(key).isJsonNull()) {
            return message.get(key).getAsString();
        }
        return "";
    }

    // Display sender and recipient of all stored messages
    public String displayStoredSendersAndRecipients() {
        loadMessages();

        StringBuilder report = new StringBuilder();

        for (JsonObject message : allMessages) {
            if (getValue(message, "Status").equalsIgnoreCase("Stored")) {
                report.append("Sender: ")
                        .append(getValue(message, "Sender"))
                        .append("\nRecipient: ")
                        .append(getValue(message, "Recipient"))
                        .append("\n\n");
            }
        }

        if (report.length() == 0) {
            return "No stored messages found.";
        }

        return report.toString();
    }

    // Find the longest stored message
    public String displayLongestStoredMessage() {
        loadMessages();

        String longest = "";

        for (JsonObject message : allMessages) {
            if (getValue(message, "Status").equalsIgnoreCase("Stored")) {
                String text = getValue(message, "Message");

                if (text.length() > longest.length()) {
                    longest = text;
                }
            }
        }

        if (longest.isEmpty()) {
            return "No stored messages found.";
        }

        return longest;
    }

    // Search for a message using its ID
    public String searchMessageByID(String id) {
        loadMessages();

        for (JsonObject message : allMessages) {
            if (getValue(message, "Message ID").equals(id)) {
                return "Recipient: "
                        + getValue(message, "Recipient")
                        + "\nMessage: "
                        + getValue(message, "Message");
            }
        }

        return "Message ID not found.";
    }

    // Search for all messages belonging to a recipient
    public String searchMessagesByRecipient(String recipient) {
        loadMessages();

        StringBuilder results = new StringBuilder();

        for (JsonObject message : allMessages) {
            if (getValue(message, "Recipient").equals(recipient)) {
                results.append("Message: ")
                        .append(getValue(message, "Message"))
                        .append("\nStatus: ")
                        .append(getValue(message, "Status"))
                        .append("\n\n");
            }
        }

        if (results.length() == 0) {
            return "No messages found for this recipient.";
        }

        return results.toString();
    }

    // Delete a message using its hash
    public boolean deleteMessageByHash(String hash) {
        loadMessages();

        boolean deleted = allMessages.removeIf(message ->
                getValue(message, "Message Hash").equals(hash));

        if (deleted) {
            saveMessages();
            updateArrays();
        }

        return deleted;
    }

    // Save the updated messages to JSON
    private void saveMessages() {
        JsonArray jsonArray = new JsonArray();

        for (JsonObject message : allMessages) {
            jsonArray.add(message);
        }

        try (FileWriter writer = new FileWriter(jsonFile)) {
            gson.toJson(jsonArray, writer);
        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    // Display a complete report of all stored messages
    public String displayStoredMessagesReport() {
        loadMessages();

        StringBuilder report = new StringBuilder();

        for (JsonObject message : allMessages) {
            if (getValue(message, "Status").equalsIgnoreCase("Stored")) {
                report.append("Message ID: ")
                        .append(getValue(message, "Message ID"))
                        .append("\nMessage Hash: ")
                        .append(getValue(message, "Message Hash"))
                        .append("\nRecipient: ")
                        .append(getValue(message, "Recipient"))
                        .append("\nMessage: ")
                        .append(getValue(message, "Message"))
                        .append("\nStatus: ")
                        .append(getValue(message, "Status"))
                        .append("\n--------------------------\n");
            }
        }

        if (report.length() == 0) {
            return "No stored messages found.";
        }

        return report.toString();
    }

    // Getters for the arrays
    public String[] getSentMessages() {
        loadMessages();
        return sentMessages.clone();
    }

    public String[] getDisregardedMessages() {
        loadMessages();
        return disregardedMessages.clone();
    }

    public String[] getStoredMessages() {
        loadMessages();
        return storedMessages.clone();
    }

    public String[] getMessageHashes() {
        loadMessages();
        return messageHashes.clone();
    }

    public String[] getMessageIDs() {
        loadMessages();
        return messageIDs.clone();
    }
}
