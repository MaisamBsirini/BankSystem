package account;

import java.util.ArrayList;
import java.util.List;

/**
 *  Composite Pattern
 * يسمح بوجود حساب رئيسي يحتوي على حسابات فرعية.
 * يعامل الحسابات الفردية والمجمّعة بنفس الطريقة.
 */
public class CompositeAccount extends Account {

    private List<Account> subAccounts = new ArrayList<>();

    public CompositeAccount(String accountId, String ownerId) {
        super(accountId, ownerId, 0);
    }

    public void addAccount(Account acc) {
        subAccounts.add(acc);
    }

    public void removeAccount(Account acc) {
        subAccounts.remove(acc);
    }

    //  حساب الرصيد الكلي من كل الحسابات الفرعية
    public double getTotalBalance() {
        double total = 0;
        for (Account acc : subAccounts) {
            total += acc.getBalance();
        }
        return total;
    }

    @Override
    public void displayAccountInfo() {
        System.out.println(" Composite Account (" + accountId + ") contains:");
        for (Account acc : subAccounts) {
            acc.displayAccountInfo();
        }
        System.out.println(" Total Balance: " + getTotalBalance());
    }
}
