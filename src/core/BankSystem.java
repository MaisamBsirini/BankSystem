package core;

import account.AccountService;
import transaction.TransactionFacade;
import customer.CustomerService;
import admin.monitoring.service.TransactionService;
import admin.monitoring.dashboard.AdminDashboard;
import admin.AdminService;

public class BankSystem {

    private static BankSystem instance;

    private CustomerService customerService;
    private AccountService accountService;
    private TransactionFacade transactionFacade;
    private AdminService adminService;
    private TransactionService adminMonitor; // جديد
    private Logger logger;
    private ErrorHandler errorHandler;

    private BankSystem() {
        this.logger = new Logger();
        this.errorHandler = new ErrorHandler();
        initializeSubsystems();
    }

    public static BankSystem getInstance() {
        if (instance == null) {
            instance = new BankSystem();
        }
        return instance;
    }

    private void initializeSubsystems() {
        System.out.println("Initializing BankSystem...");

        // 1️⃣ تهيئة الأقسام
        this.customerService = new CustomerService();
        this.accountService = new AccountService();
        this.transactionFacade = new TransactionFacade();
        this.adminService = new AdminService();

        // 2️⃣ تهيئة نظام مراقبة الأدمن وربطه بالـ Dashboard
        this.adminMonitor = new TransactionService();
        AdminDashboard dashboard = new AdminDashboard();
        adminMonitor.attach(dashboard);
        transactionFacade.setAdminMonitor(adminMonitor);

        System.out.println("All subsystems initialized successfully.");
    }

    // Getters
    public Logger getLogger() {
        return logger;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public TransactionFacade getTransactionFacade() {
        return transactionFacade;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public TransactionService getAdminMonitor() {
        return adminMonitor;
    }
}
