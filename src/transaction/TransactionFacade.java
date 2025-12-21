package transaction;

import account.Account;
import account.ActiveState;
import transaction.approval.*;
import transaction.history.TransactionLogger;
import transaction.validator.TransactionValidator;

public class TransactionFacade {

    private ApprovalHandler approvalChain;
    private TransactionValidator validator;
    private TransactionLogger logger;

    public TransactionFacade() {
        buildApprovalChain();
        validator = new TransactionValidator();
        logger = new TransactionLogger();
    }

    private void buildApprovalChain() {
        ApprovalHandler auto = new AutoApprovalHandler();
        ApprovalHandler manager = new ManagerApprovalHandler();
        ApprovalHandler admin = new AdminApprovalHandler();

        auto.setNext(manager);
        manager.setNext(admin);

        approvalChain = auto;
    }

    /**
     * معالجة معاملة مالية
     */
    public void process(Transaction tx) {
        Account from = tx.getFrom();
        Account to = tx.getTo();
        double amount = tx.getAmount();

        // 1️⃣ التحقق من صحة المعاملة باستخدام Validator
        if (!validator.validate(tx)) {
            tx.setStatus(TransactionStatus.REJECTED);
            System.out.println("Transaction " + tx.getId() + " rejected: validation failed.");
            logger.log(tx);
            return;
        }

        // 2️⃣ تمرير المعاملة عبر سلسلة الموافقات (Approval Chain)
        if (approvalChain != null) {
            approvalChain.handle(tx);
        }

        // 3️⃣ التحقق من الرصيد وحالة الحساب
        if (from.getBalance() < amount || !(from.getState() instanceof ActiveState)) {
            tx.setStatus(TransactionStatus.REJECTED);
            System.out.println("Transaction " + tx.getId() + " rejected: insufficient balance or inactive account.");
            logger.log(tx);
            return;
        }

        // 4️⃣ تنفيذ السحب والإيداع
        executeTransfer(from, to, amount);
        tx.setStatus(TransactionStatus.COMPLETED);
        System.out.println("Transaction " + tx.getId() + " completed successfully.");

        // 5️⃣ تسجيل المعاملة
        logger.log(tx);
    }

    /**
     * تنفيذ تحويل الأموال بين الحسابات
     */
    private void executeTransfer(Account from, Account to, double amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }

}
