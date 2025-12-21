package admin.rbac.strategy;

import java.util.Set;

public class SupportAccessStrategy implements AccessStrategy {

    private static final Set<String> ALLOWED_OPERATIONS =
            Set.of("VIEW_USER", "RESET_PASSWORD");

    @Override
    public boolean canAccess(String operation) {
        return ALLOWED_OPERATIONS.contains(operation);
    }
}
