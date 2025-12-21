package admin.reporting.decorator;

public class EncryptedReport extends ReportDecorator {

    public EncryptedReport(Report report) {
        super(report);
    }

    @Override
    public String generate() {
        return report.generate() + " | Encrypted";
    }
}
