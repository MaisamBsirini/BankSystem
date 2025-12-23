package test.admin;

import admin.AdminService;
import org.junit.jupiter.api.Test;

public class AdminServiceTest {

    @Test
    void testGenerateReport() {
        AdminService admin = new AdminService();
        admin.generateAdminReport();
    }
}

