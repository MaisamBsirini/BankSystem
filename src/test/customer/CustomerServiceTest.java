package test.customer;

import customer.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    @Test
    void testAddAndFindCustomer() {
        CustomerService service = new CustomerService();
        Customer c = new Customer("Omar", null);
        service.addCustomer(c);

        assertNotNull(service.findByName("Omar"));
        assertEquals(1, service.getAllCustomers().size());
    }
}

