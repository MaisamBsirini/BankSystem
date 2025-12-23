package test.account;


import account.*;
import customer.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountStateTest {

    @Test
    void testStateTransitions() {
        Customer c = new Customer("Zaid", null);
        Account acc = new SavingsAccount("ACC-10", c, 1000, 0.02);
        acc.setState(new ActiveState());

        acc.getState().freeze(acc);
        assertTrue(acc.getState() instanceof FrozenState);

        acc.getState().suspend(acc);
        assertTrue(acc.getState() instanceof SuspendedState);

        acc.getState().close(acc);
        assertTrue(acc.getState() instanceof ClosedState);
    }
}
