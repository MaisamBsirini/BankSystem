package transaction.approval;
import transaction.Transaction;


public abstract class ApprovalHandler {

    protected ApprovalHandler nextHandler;


    public void setNext(ApprovalHandler next) {
        this.nextHandler = next;
    }
    // abstract method
    public abstract void handle(Transaction tx);

}
