import java.util.HashMap;
import java.util.Scanner;

// BankAccount class to hold information about each bank account
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0; // Initial balance
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit money into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    // Transfer money to another account
    public void transfer(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            targetAccount.balance += amount;
            System.out.println("Transfer successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid transfer amount or insufficient funds.");
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolder + ", Balance: $" + balance;
    }
}

// BankY class to manage multiple bank accounts
public class BankY {
    private HashMap<String, BankAccount> accounts;
    private Scanner scanner;

    public BankY() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // Method to create a new bank account
    public void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account with this number already exists.");
            return;
        }

        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.nextLine();
        accounts.put(accountNumber, new BankAccount(accountNumber, accountHolder));
        System.out.println("Account created successfully!\n");
    }

    // Method to perform a deposit
    public void depositFunds() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            account.deposit(amount);
        }
    }

    // Method to perform a withdrawal
    public void withdrawFunds() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            account.withdraw(amount);
        }
    }

    // Method to transfer funds between accounts
    public void transferFunds() {
        BankAccount sourceAccount = getAccount("Enter source account number: ");
        if (sourceAccount != null) {
            BankAccount targetAccount = getAccount("Enter target account number: ");
            if (targetAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();  // Consume newline
                sourceAccount.transfer(targetAccount, amount);
            }
        }
    }

    // Method to get an account by number
    private BankAccount getAccount() {
        return getAccount("Enter account number: ");
    }

    private BankAccount getAccount(String prompt) {
        System.out.print(prompt);
        String accountNumber = scanner.nextLine();
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Account not found.");
            return null;
        }
        return accounts.get(accountNumber);
    }

    // Method to display all accounts
    public void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the system.");
        } else {
            System.out.println("Listing all accounts:");
            for (BankAccount account : accounts.values()) {
                System.out.println(account);
            }
        }
        System.out.println();
    }

    // Main menu for the banking system
    public void showMenu() {
        int choice;
        do {
            System.out.println("BankY - Banking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. List All Accounts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositFunds();
                    break;
                case 3:
                    withdrawFunds();
                    break;
                case 4:
                    transferFunds();
                    break;
                case 5:
                    listAccounts();
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        BankY bankY = new BankY();
        bankY.showMenu();
    }
}
