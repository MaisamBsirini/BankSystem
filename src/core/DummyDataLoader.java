package core;

import account.*;
import customer.*;
import transaction.*;
import admin.AdminService;

public class DummyDataLoader {

    public static void loadDemoData(BankSystem bankSystem) {
        System.out.println("📦 تحميل بيانات تجريبية موسعة...");

        // ===== 1. العملاء =====
        RecommendationEngine engine1 = new RecommendationEngine(new BalancedStrategy());
        RecommendationEngine engine2 = new RecommendationEngine(new BalancedStrategy());
        RecommendationEngine engine3 = new RecommendationEngine(new BalancedStrategy());

        Customer c1 = new Customer("Ahmad", engine1);
        Customer c2 = new Customer("Mona", engine2);
        Customer c3 = new Customer("Tarek", engine3);

        bankSystem.getCustomerService().addCustomer(c1);
        bankSystem.getCustomerService().addCustomer(c2);
        bankSystem.getCustomerService().addCustomer(c3);

        // ===== 2. الحسابات =====
        Account a1 = new SavingsAccount("ACC-1001", c1, 1500.0, 0.03);
        Account a2 = new CheckingAccount("ACC-1002", c2, 800.0, 5.0);
        Account a3 = new LoanAccount("ACC-1003", c3, 2000.0, 0.1);
        Account a4 = new InvestmentAccount("ACC-1004", c1, 5000.0, 0.07);

        bankSystem.getAccountService().createAccount(a1);
        bankSystem.getAccountService().createAccount(a2);
        bankSystem.getAccountService().createAccount(a3);
        bankSystem.getAccountService().createAccount(a4);

        // ===== 3. Decorators - ميزات إضافية =====
        Account decorated1 = new PremiumService(new OverdraftProtection(a2));
       // Account decorated2 = new Insurance(a1);
        decorated1.displayAccountInfo();
      //  decorated2.displayAccountInfo();

        // ===== 4. معاملات تجريبية =====
        TransactionFacade tf = bankSystem.getTransactionFacade();

        // معاملات عادية
        Transaction tx1 = new Transaction("TX-101", a1, a2, 200.0, TransactionType.TRANSFER);
        tf.process(tx1);

        Transaction tx2 = new Transaction("TX-102", a2, a3, 100.0, TransactionType.TRANSFER);
        tf.process(tx2);

        Transaction tx3 = new Transaction("TX-103", a1, a4, 300.0, TransactionType.TRANSFER);
        tf.process(tx3);

        // معاملات مجدولة / متكررة
        Transaction rtx1 = new Transaction("R-TX-201", a4, a2, 50.0, TransactionType.TRANSFER);
        tf.scheduleRecurringTransaction(rtx1, 3, 10_000); // كل 10 ثواني

        Transaction rtx2 = new Transaction("R-TX-202", a2, a1, 30.0, TransactionType.TRANSFER);
        tf.scheduleRecurringTransaction(rtx2, 2, 15_000); // كل 15 ثانية

        // ===== 5. تذاكر الدعم =====
        bankSystem.getCustomerService().sendInquiry("Ahmad", "أرغب بتحديث عنواني.");
        bankSystem.getCustomerService().sendInquiry("Mona", "مشاكل في تسجيل الدخول.");
        bankSystem.getCustomerService().sendInquiry("Tarek", "طلب استخراج كشف حساب");

        // ===== 6. تهيئة الأدمن =====
        AdminService adminService = bankSystem.getAdminService();
        adminService.generateAdminReport();  // إنشاء تقرير أولي

        System.out.println("✅ تم تحميل كل البيانات التجريبية بنجاح.\n");
    }
}
