class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {

    public InvalidAmountException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
        super(message);
    }
}

class BankAccount {

    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero."
            );
        }

        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance."
            );
        }

        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

public class Q5 {

    static BankAccount findAccount(String accountNumber)
            throws AccountNotFoundException {

        if (!accountNumber.equals("ACC101")) {
            throw new AccountNotFoundException(
                    "Account " + accountNumber + " not found."
            );
        }

        return new BankAccount("ACC101", 5000);
    }

    static void performTransaction(String accountNumber)
            throws Exception {

        // Exception can propagate from findAccount()
        BankAccount account = findAccount(accountNumber);

        account.deposit(1000);
        account.withdraw(2000);

        System.out.println("Balance = " + account.getBalance());
    }

    public static void main(String[] args) {

        try {

            System.out.println("----- BANKING APPLICATION -----");

            performTransaction("ACC101");

        } catch (AccountNotFoundException e) {

            System.out.println("Account Error: " + e.getMessage());

        } catch (InsufficientBalanceException e) {

            System.out.println("Balance Error: " + e.getMessage());

        } catch (InvalidAmountException e) {

            System.out.println("Amount Error: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            System.out.println("Banking transaction completed.");
        }
    }
}