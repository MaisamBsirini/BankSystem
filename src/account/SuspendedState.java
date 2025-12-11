package account;

import account.Account;

/**
 *  حالة الحساب الموقوف مؤقتًا.
 */
public class SuspendedState implements AccountState {
    @Override
    public void activate(Account account) {
        account.setState(new ActiveState());
        System.out.println(" Account reactivated.");
    }

    @Override
    public void freeze(Account account) {
        account.setState(new FrozenState());
        System.out.println(" Account frozen.");
    }

    @Override
    public void suspend(Account account) {
        System.out.println(" Already suspended.");
    }

    @Override
    public void close(Account account) {
        account.setState(new ClosedState());
        System.out.println(" Account closed.");
    }
}
