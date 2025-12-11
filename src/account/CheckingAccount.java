package account;

/**
 * 💳 الحساب الجاري - يرث من Account
 * يتيح السحب المتكرر برسوم بسيطة على المعاملة.
 */
public class CheckingAccount extends Account {
    private double transactionFee;

    public CheckingAccount(String accountId, String ownerId, double balance, double fee) {
        super(accountId, ownerId, balance);
        this.transactionFee = fee;
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
        System.out.println(" Checking Account: " + accountId + " | Balance: " + balance + " | Fee: " + transactionFee);
    }
}
