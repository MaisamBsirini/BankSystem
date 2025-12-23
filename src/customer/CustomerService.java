package customer;

import customer.support.SupportTicketService;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<Customer> customers = new ArrayList<>();
    private final SupportTicketService ticketService = new SupportTicketService();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("✅ Customer added: " + customer);
    }

    public Customer findByName(String name) {
        for (Customer c : customers) {
            if (c != null && c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }


    public void sendInquiry(String customerName, String message) {
        ticketService.createTicket(customerName, message);
    }

    public SupportTicketService getTicketService() {
        return ticketService;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public void listCustomers() {
        System.out.println("=== Registered Customers ===");
        for (Customer c : customers) {
            System.out.println("- " + c);
        }
    }
}
