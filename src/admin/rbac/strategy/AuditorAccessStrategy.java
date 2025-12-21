package admin.rbac.strategy;

public class AuditorAccessStrategy implements AccessStrategy {

    @Override
    public boolean canAccess(String operation) {
        // Auditor is read-only
        return operation.startsWith("VIEW");
    }
}
