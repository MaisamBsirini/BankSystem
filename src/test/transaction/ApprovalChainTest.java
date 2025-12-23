package test.transaction;

import account.*;
import customer.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import transaction.*;
import transaction.approval.*;

public class ApprovalChainTest {

    @Test
    void testApprovalChain() {
        ApprovalHandler auto = new AutoApprovalHandler();
        ApprovalHandler manager = new ManagerApprovalHandler();
        ApprovalHandler admin = new AdminApprovalHandler();
        auto.setNext(manager);
        manager.setNext(admin);

        Customer c1 = new Customer("Ali", null);
        Customer c2 = new Customer("Mona", null);
        Account from = new SavingsAccount("F1", c1, 5000, 0.02);
        Account to = new CheckingAccount("T1", c2, 500, 5);
        from.setState(new ActiveState());
        to.setState(new ActiveState());

        Transaction tx = new Transaction("TX-500", from, to, 2000, TransactionType.TRANSFER);
        auto.handle(tx);

        assertEquals(TransactionStatus.APPROVED, tx.getStatus());
        assertTrue(from.getBalance() < 5000);
        assertTrue(to.getBalance() > 500);
    }
}

