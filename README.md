# QuickChat – Programming 1A POE

## 1. Project Description

QuickChat is a Java-based messaging application developed for the Programming 1A Portfolio of Evidence (POE). The application allows users to register, log in, create messages, and manage stored messages.

The project was developed using Java and NetBeans IDE with Maven.

## 2. Project Objectives

The main objectives of QuickChat are to:

- Allow users to register and log in securely.
- Validate usernames, passwords and cellphone numbers.
- Allow users to create and send messages.
- Generate a unique message ID and message hash.
- Allow users to send, disregard or store messages.
- Save stored messages in a JSON file.
- Search, display and delete stored messages.

## 3. Technologies Used

- **Programming Language:** Java
- **IDE:** NetBeans
- **Build Tool:** Maven
- **Data Storage:** JSON
- **Testing:** JUnit
- **JSON Library:** Gson
- **Version Control:** Git and GitHub

## 4. Application Features

### Part 1: User Registration and Login

- Username validation.
- Password complexity validation.
- South African cellphone number validation.
- User registration.
- Login authentication.
- Login status messages.

### Part 2: Messaging

- Create messages.
- Generate unique message IDs.
- Generate message hashes.
- Validate message length.
- Validate recipient cellphone numbers.
- Send, disregard or store messages.

### Part 3: Message Storage and Reports

- Store messages in a JSON file.
- Display stored senders and recipients.
- Display the longest stored message.
- Search for messages using a message ID.
- Search messages by recipient.
- Delete messages using a message hash.
- Display stored message reports.

## 5. How to Run the Application

1. Open NetBeans IDE.
2. Open the `QuickChatPOE` Maven project.
3. Allow Maven to load the project dependencies.
4. Locate the `Main.java` file inside the `com.quickchat` package.
5. Right-click `Main.java`.
6. Select **Run File** or run the project.
7. Follow the instructions displayed in the console.

## 6. JSON Storage

QuickChat uses a file called `messages.json` to store message information.

The JSON file contains information such as:

- Message ID
- Message Hash
- Recipient
- Message
- Message Number
- Message Status

## 7. Testing

The application includes unit tests to check important functionality, including:

- Username validation.
- Password complexity.
- Cellphone number validation.
- Login functionality.
- Message ID generation.
- Message hash creation.
- Message validation.
- Message storage functionality.

## 8. Screenshots

Add screenshots demonstrating the following:

1. Successful user registration.
2. Unsuccessful registration validation.
3. Successful login.
4. Message creation.
5. Message sending.
6. Message storage.
7. Stored Messages menu.
8. Searching for a message.
9. Deleting a message.
10. Stored messages report.
11. Successful unit test results.

## 9. GitHub Repository

This project is managed using Git and GitHub. Changes to the project are committed and pushed to the GitHub repository.

**GitHub Repository:** Add your GitHub repository link here.

## 10. Conclusion

QuickChat demonstrates the use of Java programming concepts, object-oriented programming, methods, arrays, validation, file handling, JSON storage and unit testing.

The project provides a simple messaging application that allows users to register, log in, manage messages and view stored message information.
