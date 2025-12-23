package test.account;


import account.*;
import customer.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountDecoratorTest {

    @Test
    void testOverdraftProtection() {
        Customer c = new Customer("Omar", null);
        Account acc = new CheckingAccount("C-100", c, 100, 10);
        Account decorated = new OverdraftProtection(acc);

        decorated.withdraw(150); // ضمن حد السحب على المكشوف
        assertTrue(acc.getBalance() <= 0);
    }

    @Test
    void testInsuranceFeature() {
        Customer c = new Customer("Sara", null);
        Account acc = new SavingsAccount("S-101", c, 1000, 0.03);
        Account insured = new InsuranceFeature(acc);
        insured.displayAccountInfo();
    }
}
