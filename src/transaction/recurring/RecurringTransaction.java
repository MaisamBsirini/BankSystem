package transaction.recurring;

import transaction.Transaction;
import transaction.TransactionFacade;

/**
 * Command Pattern:
 * يمثل معاملة قابلة للتنفيذ بشكل متكرر.
 */
public class RecurringTransaction implements Runnable {

    private final Transaction transaction;
    private final TransactionFacade facade;

    public RecurringTransaction(Transaction transaction, TransactionFacade facade) {
        this.transaction = transaction;
        this.facade = facade;
    }

    @Override
    public void run() {
        System.out.println("\n🔁 Executing scheduled transaction: " + transaction.getId());
        facade.process(transaction);
    }
}
