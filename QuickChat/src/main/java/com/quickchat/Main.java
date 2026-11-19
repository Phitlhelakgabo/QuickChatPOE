package com.quickchat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MessageStore store = new MessageStore();
        store.loadStored(); // Load JSON data here later

        System.out.println("Welcome to QuickChat.");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("How many messages do you wish to enter? ");
                    int numMessages = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Entering Message " + (i+1) + " ---");
                        System.out.print("Enter Recipient (+27...): ");
                        String recipient = scanner.nextLine();
                        
                        System.out.print("Enter Message: ");
                        String text = scanner.nextLine();
                        
                        // Generate random 10 digit ID
                        String id = String.valueOf((long) (Math.random() * 9000000000L) + 1000000000L);
                        
                        Message msg = new Message(id, i + 1, recipient, text, "Pending");
                        
                        // Validate length
                        if (msg.getMessageText().length() > 250) {
                            System.out.println("Please enter a message of less than 250 characters.");
                            i--; // Force them to try again
                            continue;
                        }

                        System.out.println("Message Hash: " + msg.createMessageHash());
                        System.out.println("1) Send  2) Disregard  3) Store");
                        System.out.print("Choice: ");
                        String action = scanner.nextLine();
                        
                        if (action.equals("1")) {
                            msg.sentMessage(1);
                            store.add(msg);
                            System.out.println("Message successfully sent.");
                        } else if (action.equals("2")) {
                            msg.sentMessage(2);
                            store.add(msg);
                        } else if (action.equals("3")) {
                            msg.sentMessage(3);
                            store.add(msg);
                            System.out.println("Message successfully stored.");
                            // TODO: Add JSON save logic here
                        }
                    }
                    System.out.println("Total messages sent: " + store.sent.size());
                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                case "4":
                    // Call the sub-menu logic for Stored Messages here
                    handleStoredMessagesMenu(scanner, store);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void handleStoredMessagesMenu(Scanner scanner, MessageStore store) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("a) Display sender and recipient of all stored messages");
            System.out.println("b) Display the longest stored message");
            System.out.println("c) Search for a message ID");
            System.out.println("d) Search for messages by recipient");
            System.out.println("e) Delete a message using the message hash");
            System.out.println("f) Display full report");
            System.out.println("g) Back to Main Menu");
            System.out.print("Choice: ");
            String subChoice = scanner.nextLine().toLowerCase();

            switch (subChoice) {
                case "a":
                    for (Message m : store.stored) {
                        System.out.println("Recipient: " + m.getRecipient() + " | Message: " + m.getMessageText());
                    }
                    break;
                case "b":
                    System.out.println("Longest: " + store.longestStored());
                    break;
                case "c":
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    store.searchById(id).forEach(m -> System.out.println(m.getMessageText()));
                    break;
                case "d":
                    System.out.print("Enter Recipient: ");
                    String rec = scanner.nextLine();
                    store.searchByRecipient(rec).forEach(m -> System.out.println(m.getMessageText()));
                    break;
                case "e":
                    System.out.print("Enter Hash: ");
                    String hash = scanner.nextLine();
                    // You will need to update deleteByHash to use createMessageHash()
                    store.deleteByHash(hash); 
                    System.out.println("Deleted.");
                    break;
                case "f":
                    Report report = new Report(store);
                    System.out.println(report.generateReport());
                    break;
                case "g":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}