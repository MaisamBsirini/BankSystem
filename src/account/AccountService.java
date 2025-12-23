package account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private List<Account> accounts = new ArrayList<>();

    // 🟢 إنشاء حساب جديد
    public void createAccount(Account acc) {
        accounts.add(acc);
        System.out.println("✅ Account created: " + acc.getAccountId() + " | Owner: " + acc.getOwner());
    }

    // 🔍 البحث عن حساب
    public Account findById(String accountId) {
        for (Account acc : accounts) {
            if (acc.getAccountId().equals(accountId)) return acc;
        }
        return null;
    }

    // ✏️ تعديل الحساب (مثلاً تحديث الرصيد أو الفائدة)
    public void updateAccount(Account acc, double newBalance) {
        acc.balance = newBalance;
        System.out.println("✏️ Account updated: " + acc.getAccountId() + " | New Balance: " + acc.getBalance());
    }

    // ❌ إغلاق الحساب
    public void closeAccount(String accountId) {
        Account acc = findById(accountId);
        if (acc != null) {
            acc.setState(new ClosedState());
            System.out.println("🚫 Account " + accountId + " closed successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    // 📄 عرض جميع الحسابات
    public void listAccounts() {
        System.out.println("\n=== All Accounts ===");
        for (Account acc : accounts) {
            acc.displayAccountInfo();
        }
    }

    // 📜 إرجاع جميع الحسابات
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }
}
