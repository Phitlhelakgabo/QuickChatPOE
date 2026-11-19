
package com.quickchat;

import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Login login = new Login();
        MessageStore messageStore = new MessageStore();

        System.out.println("================================");
        System.out.println("       WELCOME TO QUICKCHAT");
        System.out.println("================================");

        // REGISTRATION
        System.out.println("\nREGISTRATION");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cellphone number (+27...): ");
        String cellphone = input.nextLine();

        login.setRegistrationDetails(
                username, password, cellphone, firstName, lastName);

        System.out.println(login.registerUser());

        if (!login.checkUserName()
                || !login.checkPasswordComplexity()
                || !login.checkCellPhoneNumber()) {

            System.out.println("Registration unsuccessful.");
            return;
        }

        // LOGIN
        System.out.println("\nLOGIN");

        System.out.print("Enter username: ");
        String enteredUsername = input.nextLine();

        System.out.print("Enter password: ");
        String enteredPassword = input.nextLine();

        login.setLoginDetails(enteredUsername, enteredPassword);

        if (!login.loginUser()) {
            System.out.println("Login failed.");
            return;
        }

        System.out.println(login.returnLoginStatus());

        // MAIN MENU
        boolean running = true;

        while (running) {

            System.out.println("\n================================");
            System.out.println("          QUICKCHAT MENU");
            System.out.println("================================");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {

                case "1":
                    sendMessages();
                    break;

                case "2":
                    String[] sent = messageStore.getSentMessages();

                    if (sent.length == 0) {
                        System.out.println("No sent messages found.");
                    } else {
                        System.out.println("\nRECENTLY SENT MESSAGES");

                        for (String message : sent) {
                            System.out.println(message);
                        }
                    }
                    break;

                case "3":
                    System.out.println("Thank you for using QuickChat.");
                    running = false;
                    break;

                case "4":
                    storedMessagesMenu(messageStore);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // SEND MESSAGES
    private static void sendMessages() {

        System.out.print("How many messages would you like to send? ");

        int numberOfMessages;

        try {
            numberOfMessages = Integer.parseInt(input.nextLine());

            if (numberOfMessages <= 0) {
                System.out.println("Enter a number greater than zero.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        int completedMessages = 0;
        int messageNumber = 0;

        while (completedMessages < numberOfMessages) {

            System.out.println("\nMESSAGE " + (completedMessages + 1));

            System.out.print("Enter recipient cellphone number: ");
            String recipient = input.nextLine();

            System.out.print("Enter your message: ");
            String messageText = input.nextLine();

            Message message = new Message(
                    recipient, messageText, messageNumber);

            if (!message.checkMessageID()) {
                System.out.println("Invalid message ID.");
                continue;
            }

            String recipientResult = message.checkRecipientCell();

            if (!recipientResult.equals(
                    "Cell phone number successfully captured.")) {
                System.out.println(recipientResult);
                continue;
            }

            String lengthResult = message.checkMessageLength();

            if (!lengthResult.equals("Message ready to send.")) {
                System.out.println(lengthResult);
                continue;
            }

            System.out.println("\n1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message");
            System.out.print("Choose an option: ");

            String option = input.nextLine();

            if (!option.equals("1")
                    && !option.equals("2")
                    && !option.equals("3")) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            System.out.println(message.SentMessage(option));

            if (option.equals("1")) {
                System.out.println(message.printMessages());
            }

            completedMessages++;
            messageNumber++;
        }
    }

    // STORED MESSAGES MENU
    private static void storedMessagesMenu(MessageStore store) {

        boolean back = false;

        while (!back) {

            System.out.println("\n================================");
            System.out.println("       STORED MESSAGES");
            System.out.println("================================");
            System.out.println("1. Display stored senders and recipients");
            System.out.println("2. Display longest stored message");
            System.out.println("3. Search message using Message ID");
            System.out.println("4. Search messages by recipient");
            System.out.println("5. Delete message using Message Hash");
            System.out.println("6. Display stored messages report");
            System.out.println("7. Back to main menu");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {

                case "1":
                    System.out.println(
                            store.displayStoredSendersAndRecipients());
                    break;

                case "2":
                    System.out.println(
                            store.displayLongestStoredMessage());
                    break;

                case "3":
                    System.out.print("Enter Message ID: ");
                    String id = input.nextLine();

                    System.out.println(store.searchMessageByID(id));
                    break;

                case "4":
                    System.out.print("Enter recipient cellphone number: ");
                    String recipient = input.nextLine();

                    System.out.println(
                            store.searchMessagesByRecipient(recipient));
                    break;

                case "5":
                    System.out.print("Enter Message Hash: ");
                    String hash = input.nextLine();

                    if (store.deleteMessageByHash(hash)) {
                        System.out.println(
                                "Message successfully deleted.");
                    } else {
                        System.out.println("Message hash not found.");
                    }
                    break;

                case "6":
                    System.out.println(
                            store.displayStoredMessagesReport());
                    break;

                case "7":
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
