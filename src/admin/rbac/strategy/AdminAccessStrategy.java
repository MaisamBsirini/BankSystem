package admin.rbac.strategy;

public class AdminAccessStrategy implements AccessStrategy {

    @Override
    public boolean canAccess(String operation) {
        // Admin can do everything
        return true;
    }
}
