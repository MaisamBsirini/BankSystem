package account;

import customer.Customer;

public class CheckingAccount extends Account {
    private double transactionFee;

    public CheckingAccount(String accountId, Customer owner, double balance, double fee) {
        super(accountId, owner, balance);
        this.transactionFee = fee;
        this.state = new ActiveState();
    }

    @Override
    public void withdraw(double amount) {
        double total = amount + transactionFee;
        if (total <= balance) {
            balance -= total;
            System.out.println(" Withdrawal " + amount + " + fee " + transactionFee + " done. New balance: " + balance);
        } else {
            System.out.println(" Not enough balance for withdrawal + fee.");
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(" Checking Account: " + accountId + " | Owner: " + owner +
                " | Balance: " + balance + " | Fee: " + transactionFee);
    }
}
