package test.transaction;


import account.*;
import customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transaction.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionFacadeTest {

    private TransactionFacade facade;
    private Account from;
    private Account to;

    @BeforeEach
    void setup() {
        facade = new TransactionFacade();
        Customer c1 = new Customer("Ahmad", null);
        Customer c2 = new Customer("Sami", null);
        from = new SavingsAccount("A1", c1, 2000, 0.02);
        to = new CheckingAccount("A2", c2, 500, 5);
        from.setState(new ActiveState());
        to.setState(new ActiveState());
    }

    @Test
    void testSuccessfulTransfer() {
        Transaction tx = new Transaction("TX-001", from, to, 1000, TransactionType.TRANSFER);
        facade.process(tx);
        assertEquals(TransactionStatus.COMPLETED, tx.getStatus());
        assertTrue(from.getBalance() < 2000);
        assertTrue(to.getBalance() > 500);
    }

    @Test
    void testRejectedTransferDueToLowBalance() {
        Transaction tx = new Transaction("TX-002", from, to, 5000, TransactionType.TRANSFER);
        facade.process(tx);
        assertEquals(TransactionStatus.REJECTED, tx.getStatus());
    }
}
