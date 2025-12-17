package customer;

public class TransactionEvent {
    private String type;          // Deposit, Withdraw, Transfer
    private double amount;
    private String accountNumber;

    public TransactionEvent(String type, double amount, String accountNumber) {
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

