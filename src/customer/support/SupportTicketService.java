package customer.support;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages support tickets (creation, viewing, replying).
 * Used by both customers and admins.
 */
public class SupportTicketService {

    private final List<SupportTicket> tickets = new ArrayList<>();

    // العميل ينشئ تذكرة جديدة
    public void createTicket(String customerName, String message) {
        SupportTicket ticket = new SupportTicket(customerName, message);
        tickets.add(ticket);
        System.out.println("📨 Ticket submitted successfully with ID #" + ticket.getId());
    }

    // عرض كل التذاكر
    public List<SupportTicket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    // عرض تذاكر غير محلولة فقط
    public List<SupportTicket> getPendingTickets() {
        List<SupportTicket> pending = new ArrayList<>();
        for (SupportTicket t : tickets) {
            if (!t.isResolved()) pending.add(t);
        }
        return pending;
    }

    // الأدمن يرد على التذكرة
    public void replyToTicket(int id, String reply) {
        for (SupportTicket t : tickets) {
            if (t.getId() == id) {
                t.reply(reply);
                System.out.println("💬 Reply sent to customer: " + t.getCustomerName());
                return;
            }
        }
        System.out.println("❌ Ticket not found!");
    }

    // عرض كل التذاكر
    public void listTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No support tickets yet.");
            return;
        }
        System.out.println("\n=== 📨 Support Tickets ===");
        for (SupportTicket t : tickets) System.out.println(t);
    }
}
