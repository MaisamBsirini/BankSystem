package admin.reporting.decorator;

public class SignedReport extends ReportDecorator {

    public SignedReport(Report report) {
        super(report);
    }

    @Override
    public String generate() {
        return report.generate() + " | Digitally Signed";
    }
}
