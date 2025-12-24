package console;

import core.BankSystem;
import account.*;
import core.DummyDataLoader;
import customer.*;
import transaction.*;
import admin.AdminService;

import java.util.Scanner;

public class MainConsole {

    private final BankSystem bankSystem;
    private final Scanner scanner;

    public MainConsole() {
        this.bankSystem = BankSystem.getInstance();
        this.scanner = new Scanner(System.in);
      //  DummyDataLoader.loadDemoData(bankSystem);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n========= BANK SYSTEM MENU =========");
            System.out.println("1. إدارة العملاء");
            System.out.println("2. إدارة الحسابات");
            System.out.println("3. تنفيذ عملية مالية");
            System.out.println("4. تقارير الأدمن");
            System.out.println("5. خروج");
            System.out.print("اختر خيارك: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> manageCustomers();
                case "2" -> manageAccounts();
                case "3" -> manageTransactions();
                case "4" -> showAdminReports();
                case "5" -> {
                    System.out.println("الخروج من النظام...");
                    running = false;
                }
                default -> System.out.println("خيار غير صحيح!");
            }
        }
    }

    // ====== قسم العملاء ======
    private void manageCustomers() {
        System.out.println("\n=== إدارة العملاء ===");
        System.out.println("1. إضافة عميل جديد");
        System.out.println("2. عرض جميع العملاء");
        System.out.println("3. إرسال استفسار / تذكرة دعم");
        System.out.print("اختر خيارك: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("اسم العميل: ");
                String name = scanner.nextLine();

                RecommendationEngine engine =
                        new RecommendationEngine(new BalancedStrategy());

                Customer customer = new Customer(name, engine);
                bankSystem.getCustomerService().addCustomer(customer);
                System.out.println("تمت إضافة العميل بنجاح.");
            }
            case "2" -> bankSystem.getCustomerService().listCustomers();
            case "3" -> {
                System.out.print("اسم العميل: ");
                String name = scanner.nextLine();
                System.out.print("نص الرسالة: ");
                String msg = scanner.nextLine();
                bankSystem.getCustomerService().sendInquiry(name, msg);
            }
            default -> System.out.println("خيار غير صحيح!");
        }
    }

    // ====== قسم الحسابات ======
    private void manageAccounts() {
        System.out.println("\n=== إدارة الحسابات ===");
        System.out.println("1. إنشاء حساب جديد");
        System.out.println("2. تعديل رصيد حساب");
        System.out.println("3. إغلاق حساب");
        System.out.println("4. عرض جميع الحسابات");
        System.out.print("اختر خيارك: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> createAccount();
            case "2" -> updateAccount();
            case "3" -> closeAccount();
            case "4" -> bankSystem.getAccountService().listAccounts();
            default -> System.out.println("خيار غير صحيح!");
        }
    }

    private void createAccount() {
        System.out.print("أدخل اسم العميل: ");
        String ownerName = scanner.nextLine();
        Customer owner = bankSystem.getCustomerService().findByName(ownerName);

        if (owner == null) {
            System.out.println("العميل غير موجود، أضفه أولاً!");
            return;
        }

        System.out.print("أدخل رقم الحساب: ");
        String id = scanner.nextLine();

        System.out.println("اختر نوع الحساب:");
        System.out.println("1. Savings\n2. Checking\n3. Loan\n4. Investment");
        String type = scanner.nextLine();

        Account account = null;

        switch (type) {
            case "1" -> {
                System.out.print("رصيد ابتدائي: ");
                double bal = Double.parseDouble(scanner.nextLine());
                System.out.print("نسبة الفائدة: ");
                double rate = Double.parseDouble(scanner.nextLine());
                account = new SavingsAccount(id, owner, bal, rate);
            }
            case "2" -> {
                System.out.print("رصيد ابتدائي: ");
                double bal = Double.parseDouble(scanner.nextLine());
                System.out.print("رسوم المعاملة: ");
                double fee = Double.parseDouble(scanner.nextLine());
                account = new CheckingAccount(id, owner, bal, fee);
            }
            case "3" -> {
                System.out.print("مبلغ القرض: ");
                double loan = Double.parseDouble(scanner.nextLine());
                System.out.print("نسبة الفائدة: ");
                double rate = Double.parseDouble(scanner.nextLine());
                account = new LoanAccount(id, owner, loan, rate);
            }
            case "4" -> {
                System.out.print("مبلغ الاستثمار: ");
                double invest = Double.parseDouble(scanner.nextLine());
                System.out.print("نسبة العائد: ");
                double rate = Double.parseDouble(scanner.nextLine());
                account = new InvestmentAccount(id, owner, invest, rate);
            }
            default -> System.out.println("نوع غير صحيح!");
        }

        if (account != null) {
            bankSystem.getAccountService().createAccount(account);
            System.out.println("تم إنشاء الحساب بنجاح.");
        }
    }

    private void updateAccount() {
        System.out.print("أدخل رقم الحساب: ");
        String id = scanner.nextLine();
        Account acc = bankSystem.getAccountService().findById(id);
        if (acc == null) {
            System.out.println("الحساب غير موجود.");
            return;
        }
        System.out.print("الرصيد الجديد: ");
        double newBalance = Double.parseDouble(scanner.nextLine());
        bankSystem.getAccountService().updateAccount(acc, newBalance);
    }

    private void closeAccount() {
        System.out.print("أدخل رقم الحساب لإغلاقه: ");
        String id = scanner.nextLine();
        bankSystem.getAccountService().closeAccount(id);
    }

    // ====== قسم المعاملات ======
    private void manageTransactions() {
        System.out.println("\n=== تنفيذ عملية مالية ===");
        System.out.println("1. إيداع");
        System.out.println("2. سحب");
        System.out.println("3. تحويل بين حسابين");
        System.out.println("4. جدولة معاملة متكررة");
        System.out.print("اختر خيارك: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> deposit();
            case "2" -> withdraw();
            case "3" -> transfer();
            case "4" -> scheduleRecurringTransaction();
            default -> System.out.println("خيار غير صحيح!");
        }
    }

    private void deposit() {
        System.out.print("رقم الحساب: ");
        String id = scanner.nextLine();
        Account acc = bankSystem.getAccountService().findById(id);
        if (acc == null) {
            System.out.println("الحساب غير موجود.");
            return;
        }
        System.out.print("المبلغ: ");
        double amt = Double.parseDouble(scanner.nextLine());
        acc.deposit(amt);
    }

    private void withdraw() {
        System.out.print("رقم الحساب: ");
        String id = scanner.nextLine();
        Account acc = bankSystem.getAccountService().findById(id);
        if (acc == null) {
            System.out.println("الحساب غير موجود.");
            return;
        }
        System.out.print("المبلغ: ");
        double amt = Double.parseDouble(scanner.nextLine());
        acc.withdraw(amt);
    }

    private void transfer() {
        System.out.print("من رقم الحساب: ");
        String fromId = scanner.nextLine();
        System.out.print("إلى رقم الحساب: ");
        String toId = scanner.nextLine();
        System.out.print("المبلغ: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Account from = bankSystem.getAccountService().findById(fromId);
        Account to = bankSystem.getAccountService().findById(toId);

        if (from == null || to == null) {
            System.out.println("أحد الحسابين غير موجود.");
            return;
        }

        Transaction tx = new Transaction("TX-" + System.currentTimeMillis(), from, to, amount, TransactionType.TRANSFER);
        bankSystem.getTransactionFacade().process(tx);
    }

    private void scheduleRecurringTransaction() {
        System.out.print("من رقم الحساب: ");
        String fromId = scanner.nextLine();
        System.out.print("إلى رقم الحساب: ");
        String toId = scanner.nextLine();
        System.out.print("المبلغ: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("التكرار كل كم ثانية؟ ");
        long repeat = Long.parseLong(scanner.nextLine());

        Account from = bankSystem.getAccountService().findById(fromId);
        Account to = bankSystem.getAccountService().findById(toId);
        if (from == null || to == null) {
            System.out.println("أحد الحسابين غير موجود.");
            return;
        }

        Transaction tx = new Transaction("R-TX-" + System.currentTimeMillis(), from, to, amount, TransactionType.TRANSFER);
        bankSystem.getTransactionFacade().scheduleRecurringTransaction(tx, 3, repeat);
    }

    // ====== تقارير الأدمن ======
    private void showAdminReports() {
        System.out.println("\n=== قائمة الأدمن ===");
        System.out.println("1. عرض تقرير إداري");
        System.out.println("2. عرض كل تذاكر العملاء");
        System.out.println("3. الرد على تذكرة");
        System.out.println("4. إيقاف جميع المعاملات المجدولة");
        System.out.print("اختر خيارك: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> bankSystem.getAdminService().generateAdminReport();
            case "2" -> bankSystem.getAdminService()
                    .viewTickets(bankSystem.getCustomerService().getTicketService());
            case "3" -> {
                System.out.print("أدخل رقم التذكرة: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("اكتب الرد: ");
                String reply = scanner.nextLine();
                bankSystem.getAdminService()
                        .replyToTicket(bankSystem.getCustomerService().getTicketService(), id, reply);
            }
            case "4" -> bankSystem.getAdminService().stopAllScheduledTransactions();
            default -> System.out.println("خيار غير صحيح!");
        }
    }

    // ====== Main ======
    public static void main(String[] args) {
        new MainConsole().start();
    }
}
