package admin.rbac.model;

import admin.rbac.strategy.AccessStrategy;

public class User {

    private final String userId;
    private AccessStrategy accessStrategy;

    public User(String userId, AccessStrategy accessStrategy) {
        this.userId = userId;
        this.accessStrategy = accessStrategy;
    }

    public boolean requestAccess(String operation) {
        return accessStrategy.canAccess(operation);
    }

    public void changeRole(AccessStrategy newStrategy) {
        this.accessStrategy = newStrategy;
    }

    public String getUserId() {
        return userId;
    }
}
