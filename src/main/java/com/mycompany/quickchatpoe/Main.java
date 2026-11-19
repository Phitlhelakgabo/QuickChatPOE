
package com.quickchat;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("================================");
        System.out.println("       WELCOME TO QUICKCHAT");
        System.out.println("================================");

        // PART 1: REGISTRATION

        System.out.println("\n===== USER REGISTRATION =====");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Create username: ");
        String username = input.nextLine();

        System.out.print("Create password: ");
        String password = input.nextLine();

        System.out.print("Enter cellphone number (+27...): ");
        String cellPhone = input.nextLine();

        login.setRegistrationDetails(
                username,
                password,
                cellPhone,
                firstName,
                lastName
        );

        System.out.println("\n" + login.registerUser());

        if (!login.checkUserName()
                || !login.checkPasswordComplexity()
                || !login.checkCellPhoneNumber()) {

            System.out.println("Registration failed. Check your details.");
            input.close();
            return;
        }

        // PART 1: LOGIN

        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter username: ");
        String enteredUsername = input.nextLine();

        System.out.print("Enter password: ");
        String enteredPassword = input.nextLine();

        login.setLoginDetails(enteredUsername, enteredPassword);

        if (!login.loginUser()) {

            System.out.println(login.returnLoginStatus());
            input.close();
            return;
        }

        System.out.println(login.returnLoginStatus());
        System.out.println("\nWelcome to QuickChat.");

        // PART 2: MESSAGE COUNTER

        int choice = 0;
        int messageNumber = 0;

        // This object allows us to read the total sent count.
        Message messageCounter = new Message(
                "+27718693002",
                "Counter check",
                0
        );

        // PART 2: MAIN MENU

        while (choice != 3) {

            System.out.println("\n========== QUICKCHAT MENU ==========");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            if (!input.hasNextInt()) {

                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("How many messages would you like to send? ");

                    if (!input.hasNextInt()) {

                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                        break;
                    }

                    int numberOfMessages = input.nextInt();
                    input.nextLine();

                    if (numberOfMessages <= 0) {

                        System.out.println("Enter at least one message.");
                        break;
                    }

                    int completedMessages = 0;

                    while (completedMessages < numberOfMessages) {

                        System.out.println("\n===== MESSAGE "
                                + (messageNumber + 1) + " =====");

                        System.out.print("Enter recipient number (+27...): ");
                        String recipient = input.nextLine();

                        System.out.print("Enter your message: ");
                        String messageText = input.nextLine();

                        Message message = new Message(
                                recipient,
                                messageText,
                                messageNumber
                        );

                        // Validate message ID

                        if (!message.checkMessageID()) {

                            System.out.println("Invalid message ID.");
                            continue;
                        }

                        // Validate recipient

                        String recipientStatus = message.checkRecipientCell();

                        if (!recipientStatus.toLowerCase().contains("successfully")) {

                            System.out.println(recipientStatus);
                            continue;
                        }

                        System.out.println(recipientStatus);

                        // Validate message length

                        String lengthStatus = message.checkMessageLength();

                        if (!lengthStatus.equals("Message ready to send.")) {

                            System.out.println(lengthStatus);
                            continue;
                        }

                        System.out.println(lengthStatus);

                        // Message options

                        System.out.println("\n1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message");
                        System.out.print("Choose an option: ");

                        String messageChoice = input.nextLine();

                        if (!messageChoice.equals("1")
                                && !messageChoice.equals("2")
                                && !messageChoice.equals("3")) {

                            System.out.println("Invalid choice. Try again.");
                            continue;
                        }

                        System.out.println(message.SentMessage(messageChoice));

                        // Display message details when sent

                        if (messageChoice.equals("1")) {

                            System.out.println("\n===== MESSAGE DETAILS =====");
                            System.out.println(message.printMessages());

                        }

                        messageNumber++;
                        completedMessages++;
                    }

                    System.out.println("\nMessage session completed.");

                    System.out.println("Total messages sent: "
                            + messageCounter.returnTotalMessagess());

                    break;

                case 2:

                    System.out.println("Coming Soon.");
                    break;

                case 3:

                    System.out.println("\nThank you for using QuickChat!");

                    System.out.println("Total messages sent: "
                            + messageCounter.returnTotalMessagess());

                    break;

                default:

                    System.out.println("Invalid option. Choose 1, 2 or 3.");
            }
        }

        input.close();
    }
}
