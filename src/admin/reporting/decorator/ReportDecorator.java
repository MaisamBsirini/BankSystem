package admin.reporting.decorator;

public abstract class ReportDecorator implements Report {

    protected final Report report;

    protected ReportDecorator(Report report) {
        this.report = report;
    }
}
