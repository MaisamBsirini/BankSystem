package admin.reporting.decorator;

public class FilteredReport extends ReportDecorator {

    public FilteredReport(Report report) {
        super(report);
    }

    @Override
    public String generate() {
        return report.generate() + " | Filtered";
    }
}
