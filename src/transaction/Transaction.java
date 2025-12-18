package transaction;

import account.Account;

public class Transaction {

    private String id;
    private Account from;
    private Account to;
    private double amount;
    private TransactionType type;
    private TransactionStatus status;

    public Transaction(String id, Account from, Account to, double amount, TransactionType type) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
        this.status = TransactionStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
