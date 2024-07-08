import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    // Lists to store user information and account balances
    private static List<String> usernames = new ArrayList<String>();
    private static List<String> passwords = new ArrayList<String>();
    private static List<Double> checkingsBalances = new ArrayList<Double>();
    private static List<Double> savingsBalances = new ArrayList<Double>();
    private static List<List<String>> accountInfo = new ArrayList<List<String>>(); // Store personal info as list of lists

    public static void main(String[] args) {
        // List of choices for users
        List<String> choices = new ArrayList<String>();
        choices.add("1. Create a new account");
        choices.add("2. Login to an existing account");
        choices.add("3. Delete existing account");
        choices.add("4. Exit");

        // Create a Scanner object
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display the choices to the user
            System.out.println("\nHello! Welcome to Apex Banking! Please choose one of the following options to proceed:");
            for (String choice : choices) {
                System.out.println(choice);
            }

            // Read the user's choice
            System.out.print("Enter your choice (1, 2, 3, or 4): ");
            String userChoice = scanner.nextLine();

            // Handle user choice
            switch (userChoice) {
                case "1":
                    createNewAccount(scanner);
                    break;
                case "2":
                    login(scanner);
                    break;
                case "3":
                    deleteAccount(scanner);
                    break;
                case "4":
                    exit = true;
                    System.out.println("Thank you for using Apex Banking. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void createNewAccount(Scanner scanner) {
        System.out.println("New account: Create a unique username of your choice:");
        String newUsername = scanner.nextLine();

        // Validate username
        if (!isValidInput(newUsername)) {
            System.out.println("Invalid username. It must contain at least 6 characters. Please try again.");
            return;
        }

        // Verifies if the username has been taken
        if (usernames.contains(newUsername)) {
            System.out.println("Username already taken. Please choose another username.");
        } else {
            usernames.add(newUsername);
            System.out.println("Your new username '" + newUsername + "' has been established. Please create your password:");
            String newPassword = scanner.nextLine();

            // Validate password
            if (!isValidInput(newPassword)) {
                System.out.println("Invalid password. It must contain at least 6 characters. Please try again.");
                usernames.remove(newUsername); // Remove the username if password is invalid
                return;
            }

            passwords.add(newPassword);
            checkingsBalances.add(0.00); // Initialize checkings balance
            savingsBalances.add(0.00);   // Initialize savings balance
            List<String> userInfo = new ArrayList<>(); // Initialize personal info
            accountInfo.add(userInfo);
            System.out.println("Your new account has been created successfully.");
        }
    }

    private static boolean isValidInput(String input) {
        // Check if input contains at least 6 characters
        return input.length() >= 6;
    }

    private static void login(Scanner scanner) {
        System.out.println("Existing account: Enter your username:");
        String existingUsername = scanner.nextLine();

        if (usernames.contains(existingUsername)) {
            System.out.println("Enter your password:");
            String existingPassword = scanner.nextLine();
            int userIndex = usernames.indexOf(existingUsername);

            if (passwords.get(userIndex).equals(existingPassword)) {
                System.out.println("Login successful. Welcome, " + existingUsername + "!");
                manageAccount(scanner, userIndex);
            } else {
                System.out.println("Incorrect password. Please try again.");
            }
        } else {
            System.out.println("Username not found. Please try again.");
        }
    }

    private static void deleteAccount(Scanner scanner) {
        System.out.println("Delete account: Enter your username:");
        String deleteUsername = scanner.nextLine();

        if (usernames.contains(deleteUsername)) {
            System.out.println("Enter your password:");
            String deletePassword = scanner.nextLine();

            int userIndex = usernames.indexOf(deleteUsername);
            if (passwords.get(userIndex).equals(deletePassword)) {
                usernames.remove(userIndex);
                passwords.remove(userIndex);
                checkingsBalances.remove(userIndex);
                savingsBalances.remove(userIndex);
                accountInfo.remove(userIndex); // Remove personal info
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Incorrect password. Please try again.");
            }
        } else {
            System.out.println("Username not found. Please try again.");
        }
    }

    private static void manageAccount(Scanner scanner, int userIndex) {
        List<String> accountOptions = new ArrayList<String>();
        accountOptions.add("1. Checkings");
        accountOptions.add("2. Savings");
        accountOptions.add("3. Logout");

        if (accountInfo.get(userIndex).isEmpty()) {
            System.out.println("Please provide your account information before proceeding:");
            System.out.print("Enter your full name: ");
            String fullName = scanner.nextLine();
            System.out.print("Enter your house address: ");
            String houseAddress = scanner.nextLine();
            System.out.print("Enter your email address: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();

            List<String> userInfo = accountInfo.get(userIndex);
            userInfo.add(fullName);
            userInfo.add(houseAddress);
            userInfo.add(emailAddress);
            userInfo.add(phoneNumber);

            System.out.println("Account information saved successfully.");
        }

        boolean logout = false;
        while (!logout) {
            System.out.println("\nSelect the account type:");
            for (String option : accountOptions) {
                System.out.println(option);
            }
            String accountType = scanner.nextLine();

            switch (accountType) {
                case "1":
                    manageCheckings(scanner, userIndex);
                    break;
                case "2":
                    manageSavings(scanner, userIndex);
                    break;
                case "3":
                    logout = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void manageCheckings(Scanner scanner, int userIndex) {
        List<String> checkingsOptions = new ArrayList<String>();
        checkingsOptions.add("1. View Balance");
        checkingsOptions.add("2. Deposit");
        checkingsOptions.add("3. Withdraw");
        checkingsOptions.add("4. Back");

        boolean back = false;
        while (!back) {
            System.out.println("\nCheckings Account:");
            for (String option : checkingsOptions) {
                System.out.println(option);
            }
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Current balance: $" + checkingsBalances.get(userIndex));
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character
                    checkingsBalances.set(userIndex, checkingsBalances.get(userIndex) + depositAmount);
                    System.out.println("Deposit successful. New balance: $" + checkingsBalances.get(userIndex));
                    break;
                case "3":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character
                    if (withdrawAmount <= checkingsBalances.get(userIndex)) {
                        checkingsBalances.set(userIndex, checkingsBalances.get(userIndex) - withdrawAmount);
                        System.out.println("Withdrawal successful. New balance: $" + checkingsBalances.get(userIndex));
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void manageSavings(Scanner scanner, int userIndex) {
        List<String> savingsOptions = new ArrayList<String>();
        savingsOptions.add("1. View Balance");
        savingsOptions.add("2. Deposit");
        savingsOptions.add("3. Withdraw");
        savingsOptions.add("4. Back");

        boolean back = false;
        while (!back) {
            System.out.println("\nSavings Account:");
            for (String option : savingsOptions) {
                System.out.println(option);
            }
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Current balance: $" + savingsBalances.get(userIndex));
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character
                    savingsBalances.set(userIndex, savingsBalances.get(userIndex) + depositAmount);
                    System.out.println("Deposit successful. New balance: $" + savingsBalances.get(userIndex));
                    break;
                case "3":
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character
                    if (withdrawAmount <= savingsBalances.get(userIndex)) {
                        savingsBalances.set(userIndex, savingsBalances.get(userIndex) - withdrawAmount);
                        System.out.println("Withdrawal successful. New balance: $" + savingsBalances.get(userIndex));
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}