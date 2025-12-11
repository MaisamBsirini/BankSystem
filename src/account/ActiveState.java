package account;

import account.Account;

/**
 *  حالة الحساب النشط - يمكن تنفيذ كل العمليات.
 */
public class ActiveState implements AccountState {
    @Override
    public void activate(Account account) {
        System.out.println("Account already active.");
    }

    @Override
    public void freeze(Account account) {
        account.setState(new FrozenState());
        System.out.println(" Account frozen.");
    }

    @Override
    public void suspend(Account account) {
        account.setState(new SuspendedState());
        System.out.println("Account suspended.");
    }

    @Override
    public void close(Account account) {
        account.setState(new ClosedState());
        System.out.println(" Account closed.");
    }
}
