package admin.rbac.strategy;

public interface AccessStrategy {
    boolean canAccess(String operation);
}
