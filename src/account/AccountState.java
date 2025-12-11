package account;

/**
 *  واجهة حالة الحساب - State Pattern
 * تحدد سلوك الحساب بناءً على حالته.
 */
import account.Account;

public interface AccountState {
    void activate(Account account);
    void freeze(Account account);
    void suspend(Account account);
    void close(Account account);
}
