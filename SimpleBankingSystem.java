import java.util.Scanner;

// Class representing a bank account
class BankAccount {
    // Private fields to ensure data encapsulation
    private String accountHolderName;
    private double balance;

    // Constructor to initialize account details
    public BankAccount(String name, double initialBalance) {
        accountHolderName = name;
        balance = initialBalance;
    }

    // Method to display current balance
    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited ₹" + amount);
            checkBalance();
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew ₹" + amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    // Method to get account holder name
    public String getAccountHolderName() {
        return accountHolderName;
    }
}

// Main class containing the menu and user interaction
public class SimpleBankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Create an account
        System.out.println("Welcome to the Simple Banking System!");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit amount: ₹");
        double initialBalance = sc.nextDouble();

        // Create an account object
        BankAccount account = new BankAccount(name, initialBalance);

        // Step 2: Display menu repeatedly until user exits
        int choice;
        do {
            System.out.println("\n--- Banking Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for banking with us, " + account.getAccountHolderName() + "!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        sc.close(); // Close scanner
    }
}
