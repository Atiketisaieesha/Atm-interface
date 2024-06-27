import java.util.ArrayList;
import java.util.Scanner;

// Class representing a bank account
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private ArrayList<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}

// Class representing an ATM
public class ATM {
    private BankAccount currentAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.currentAccount = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + currentAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    System.out.println("Current Balance: $" + currentAccount.getBalance());
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (currentAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                        System.out.println("Current Balance: $" + currentAccount.getBalance());
                    }
                    break;
                case 4:
                    System.out.println("Transfer feature not implemented.");
                    break;
                case 5:
                    System.out.println("Transaction History:");
                    for (String transaction : currentAccount.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        // Example usage:
        BankAccount account = new BankAccount("123456", "John Doe", 1000.0); // Initial balance $1000
        ATM atm = new ATM(account);
        atm.displayMenu();
    }
}
