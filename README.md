Apex Banking Application
Welcome to the Apex Banking Application! This application allows users to create new accounts, log into existing accounts, delete accounts, and manage their checkings and savings accounts.

Features
Create a New Account: Users can create a new account with a unique username and password.
New users receive a $500 sign-up bonus in their savings account.
Login to an Existing Account: Users can log into their existing accounts using their username and password.
Delete Existing Account: Users can delete their accounts by providing their username and password.
Manage Accounts:
Checkings Account: View balance, deposit funds, and withdraw funds.
Savings Account: View balance, deposit funds, and withdraw funds.
How to Run the Application
Prerequisites:

Java Development Kit (JDK) installed on your machine.
A terminal or command prompt to run the Java application.
Steps:

Save the provided Java code in a file named Main.java.
Open your terminal or command prompt.
Navigate to the directory where Main.java is saved.
Compile the Java program using the following command:
bash
Copy code
javac Main.java
Run the compiled Java program using the following command:
bash
Copy code
java Main
Application Flow
Welcome Screen:

The application starts with a welcome message and presents the following options:
markdown
Copy code
1. Create a new account
2. Login to an existing account
3. Delete existing account
4. Exit
Create a New Account:

The user is prompted to create a unique username and password.
If the username is valid and not already taken, the account is created with an initial balance of $0.00 for the checkings account and $500.00 for the savings account.
Login to an Existing Account:

The user is prompted to enter their username and password.
If the credentials are correct, the user is logged in and can manage their account.
Delete Existing Account:

The user is prompted to enter their username and password.
If the credentials are correct, the account is deleted along with all associated information.
Manage Accounts:

After logging in, the user can manage their checkings and savings accounts.
Options include viewing balances, depositing funds, and withdrawing funds.
License
This project is licensed under the MIT License - see the LICENSE file for details.
