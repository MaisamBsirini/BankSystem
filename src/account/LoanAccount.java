package account;

/**
 *  حساب القرض - يحتوي على مبلغ القرض المتبقي ونسبة الفائدة.
 */
public class LoanAccount extends Account {
    private double loanAmount;
    private double remainingAmount;
    private double interestRate;

    public LoanAccount(String accountId, String ownerId, double loanAmount, double interestRate) {
        super(accountId, ownerId, 0);
        this.loanAmount = loanAmount;
        this.remainingAmount = loanAmount;
        this.interestRate = interestRate;
    }

    // 🧾 سداد جزء من القرض
    public void payInstallment(double amount) {
        if (amount > 0 && amount <= remainingAmount) {
            remainingAmount -= amount;
            System.out.println(" Payment of " + amount + " made. Remaining: " + remainingAmount);
        } else {
            System.out.println(" Invalid payment amount.");
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(" Loan Account: " + accountId + " | Remaining: " + remainingAmount + " | Interest: " + interestRate);
    }
}
