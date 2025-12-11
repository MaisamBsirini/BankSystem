package account;

/**
 * حساب التوفير - يرث من Account
 * يتيح الفائدة الدورية على الرصيد.
 */
public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountId, String ownerId, double balance, double interestRate) {
        super(accountId, ownerId, balance);
        this.interestRate = interestRate;
    }

    //  دالة لحساب الفائدة الشهرية
    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println(" Interest added: " + interest + ". New balance: " + balance);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(" Savings Account: " + accountId + " | Balance: " + balance + " | Interest: " + interestRate);
    }
}
