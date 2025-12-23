package customer.support;

/**
 * Represents a customer support inquiry or complaint.
 */
public class SupportTicket {

    private static int counter = 1;

    private final int id;
    private final String customerName;
    private final String message;
    private String reply;
    private boolean resolved;

    public SupportTicket(String customerName, String message) {
        this.id = counter++;
        this.customerName = customerName;
        this.message = message;
        this.reply = "No reply yet";
        this.resolved = false;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMessage() {
        return message;
    }

    public String getReply() {
        return reply;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void reply(String response) {
        this.reply = response;
        this.resolved = true;
    }

    @Override
    public String toString() {
        return "\nTicket #" + id +
                " | Customer: " + customerName +
                "\nMessage: " + message +
                "\nReply: " + reply +
                "\nStatus: " + (resolved ? " Resolved" : " Pending");
    }
}
