package transaction.approval;

import account.Account;
import account.ActiveState;
import transaction.Transaction;
import transaction.TransactionStatus;

public class ManagerApprovalHandler extends ApprovalHandler {

    @Override
    public void handle(Transaction tx) {
        Account from = tx.getFrom();
        Account to = tx.getTo();
        double amount = tx.getAmount();

        if (amount <= 10000) { // مثال: معاملات متوسطة يوافق عليها المدير
            if (from.getBalance() >= amount && from.getState() instanceof ActiveState) {
                from.withdraw(amount);
                to.deposit(amount);
                tx.setStatus(TransactionStatus.APPROVED);
            } else {
                tx.setStatus(TransactionStatus.REJECTED);
            }
        } else if (nextHandler != null) {
            nextHandler.handle(tx); // تمرير للـ Admin
        }
    }
}
