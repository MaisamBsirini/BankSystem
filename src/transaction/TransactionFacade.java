package transaction;

import account.Account;
import account.ActiveState;
import customer.TransactionEvent;
import admin.monitoring.service.TransactionService;
import admin.reporting.decorator.*;
import transaction.approval.*;
import transaction.history.TransactionLogger;
import transaction.validator.TransactionValidator;

public class TransactionFacade {

    private ApprovalHandler approvalChain;
    private TransactionValidator validator;
    private TransactionLogger logger;
    private TransactionService adminMonitor; // جديد: نظام مراقبة الأدمن

    public TransactionFacade() {
        buildApprovalChain();
        validator = new TransactionValidator();
        logger = new TransactionLogger();
    }

    // إعداد سلسلة الموافقات
    private void buildApprovalChain() {
        ApprovalHandler auto = new AutoApprovalHandler();
        ApprovalHandler manager = new ManagerApprovalHandler();
        ApprovalHandler admin = new AdminApprovalHandler();

        auto.setNext(manager);
        manager.setNext(admin);

        approvalChain = auto;
    }

    // ربط نظام المراقبة للأدمن
    public void setAdminMonitor(TransactionService adminMonitor) {
        this.adminMonitor = adminMonitor;
    }

    /**
     * معالجة معاملة مالية كاملة
     */
    public void process(Transaction tx) {
        Account from = tx.getFrom();
        Account to = tx.getTo();
        double amount = tx.getAmount();

        // 1️⃣ التحقق من صحة المعاملة
        if (!validator.validate(tx)) {
            tx.setStatus(TransactionStatus.REJECTED);
            System.out.println("Transaction " + tx.getId() + " rejected: validation failed.");
            logger.log(tx);
            notifyAdmin(tx);
            return;
        }

        // 2️⃣ تمريرها عبر سلسلة الموافقات
        if (approvalChain != null) {
            approvalChain.handle(tx);
        }

        // 3️⃣ التحقق من الرصيد وحالة الحساب
        if (from.getBalance() < amount || !(from.getState() instanceof ActiveState)) {
            tx.setStatus(TransactionStatus.REJECTED);
            System.out.println("Transaction " + tx.getId() + " rejected: insufficient balance or inactive account.");
            logger.log(tx);
            notifyAdmin(tx);
            return;
        }

        // 4️⃣ تنفيذ العملية المالية
        executeTransfer(from, to, amount);
        tx.setStatus(TransactionStatus.COMPLETED);
        System.out.println("Transaction " + tx.getId() + " completed successfully.");

        // 5️⃣ تسجيلها وإرسال إشعارات
        logger.log(tx);
        notifyAdmin(tx);
    }

    /**
     * تنفيذ عملية التحويل الفعلية
     */
    private void executeTransfer(Account from, Account to, double amount) {
        from.withdraw(amount);
        to.deposit(amount);

        // إشعار العميل المرسل
        if (from.getOwner() != null) {
            TransactionEvent event = new TransactionEvent("Transfer Sent", amount, from.getAccountId());
            from.getOwner().update(event);
        }

        // إشعار العميل المستلم
        if (to.getOwner() != null) {
            TransactionEvent event = new TransactionEvent("Transfer Received", amount, to.getAccountId());
            to.getOwner().update(event);
        }
    }

    /**
     * إشعار الأدمن في كل عملية
     */
    private void notifyAdmin(Transaction tx) {
        if (adminMonitor != null) {
            String msg = "TX [" + tx.getId() + "] → " + tx.getType() +
                    " | Amount: " + tx.getAmount() + " | Status: " + tx.getStatus();
            adminMonitor.processTransaction(tx.getId(), tx.getAmount());
            System.out.println("[ADMIN MONITOR] " + msg);
        }
    }

    /**
     * إنشاء تقرير بسيط عن المعاملات
     */
    public void generateReport() {
        Report report = new BaseReport();
        report = new FilteredReport(report);
        report = new EncryptedReport(report);
        report = new SignedReport(report);

        System.out.println("\n=== ADMIN REPORT ===");
        System.out.println(report.generate());
        System.out.println("=====================\n");
    }

    // إضافة دعم للمعاملات المجدولة
    public void scheduleRecurringTransaction(Transaction tx, long delaySeconds, long repeatSeconds) {
        transaction.recurring.RecurringTransaction task = new transaction.recurring.RecurringTransaction(tx, this);
        transaction.recurring.RecurringTransactionScheduler scheduler = new transaction.recurring.RecurringTransactionScheduler();
        scheduler.scheduleRecurring(task, delaySeconds, repeatSeconds);
    }

    public TransactionLogger getLogger() {
        return logger;
    }
}
