package admin.monitoring;

import admin.monitoring.dashboard.AdminDashboard;
import admin.monitoring.service.TransactionService;

public class MonitoringApplication {

    public static void main(String[] args) {

        TransactionService transactionService = new TransactionService();
        AdminDashboard dashboard = new AdminDashboard();

        transactionService.attach(dashboard);

        transactionService.processTransaction("TX-1001", 500.00);
        transactionService.processTransaction("TX-1002", 1200.75);
    }
}
