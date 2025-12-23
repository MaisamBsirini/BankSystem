package test.account;

import account.*;
import account.AccountService;
import account.CheckingAccount;
import account.SavingsAccount;
import customer.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    @Test
    void testAddAndFindAccount() {
        AccountService service = new AccountService();
        Customer c = new Customer("Omar", null);
        Account acc = new SavingsAccount("ACC-1", c, 1000, 0.03);
        service.createAccount(acc);

        Account found = service.findById("ACC-1");
        assertNotNull(found);
        assertEquals(1000, found.getBalance());
    }

    @Test
    void testGetAllAccounts() {
        AccountService service = new AccountService();
        Customer c1 = new Customer("Ali", null);
        Customer c2 = new Customer("Mona", null);
        service.createAccount(new CheckingAccount("C1", c1, 500, 5));
        service.createAccount(new SavingsAccount("S1", c2, 1500, 0.02));

        assertEquals(2, service.getAllAccounts().size());
    }
}

