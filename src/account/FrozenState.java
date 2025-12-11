package account;

import account.Account;

/**
 *  حالة الحساب المجمد - يمنع السحب أو الإيداع.
 */
public class FrozenState implements AccountState {
    @Override
    public void activate(Account account) {
        account.setState(new ActiveState());
        System.out.println(" Account reactivated.");
    }

    @Override
    public void freeze(Account account) {
        System.out.println(" Account already frozen.");
    }

    @Override
    public void suspend(Account account) {
        account.setState(new SuspendedState());
        System.out.println(" Account suspended.");
    }

    @Override
    public void close(Account account) {
        account.setState(new ClosedState());
        System.out.println(" Account closed.");
    }
}
