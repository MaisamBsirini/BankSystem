package test.customer.support;


import customer.support.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SupportTicketServiceTest {

    @Test
    void testCreateAndReplyToTicket() {
        SupportTicketService service = new SupportTicketService();
        service.createTicket("Ahmad", "My account is frozen.");

        SupportTicket t = service.getAllTickets().get(0);
        assertEquals("Ahmad", t.getCustomerName());
        assertFalse(t.isResolved());

        service.replyToTicket(t.getId(), "Your account has been reactivated.");
        assertTrue(t.isResolved());
        assertEquals("Your account has been reactivated.", t.getReply());
    }
}
