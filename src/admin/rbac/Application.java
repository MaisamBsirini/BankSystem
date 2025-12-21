package admin.rbac;

import admin.rbac.model.User;
import admin.rbac.strategy.*;

public class Application {

    public static void main(String[] args) {

        User admin = new User("U1", new AdminAccessStrategy());
        User auditor = new User("U2", new AuditorAccessStrategy());
        User support = new User("U3", new SupportAccessStrategy());

        testAccess(admin, "GENERATE_REPORT");
        testAccess(auditor, "GENERATE_REPORT");
        testAccess(auditor, "VIEW_REPORT");
        testAccess(support, "RESET_PASSWORD");
        testAccess(support, "DELETE_USER");
    }

    private static void testAccess(User user, String operation) {
        System.out.println(
                "User " + user.getUserId() +
                        " requesting " + operation +
                        " → " + (user.requestAccess(operation) ? "ALLOWED" : "DENIED")
        );
    }
}
