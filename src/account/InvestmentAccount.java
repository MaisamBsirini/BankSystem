package account;

/**
 *  حساب استثماري - يتعامل مع مبالغ استثمارية.
 */
public class InvestmentAccount extends Account {
    private double investmentAmount;
    private double returnRate;

    public InvestmentAccount(String accountId, String ownerId, double investmentAmount, double returnRate) {
        super(accountId, ownerId, investmentAmount);
        this.investmentAmount = investmentAmount;
        this.returnRate = returnRate;
    }

    public void calculateReturns() {
        double profit = investmentAmount * returnRate;
        balance += profit;
        System.out.println(" Profit added: " + profit + ". New balance: " + balance);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(" Investment Account: " + accountId + " | Balance: " + balance + " | Return rate: " + returnRate);
    }
}
