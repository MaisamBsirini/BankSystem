package admin.reporting;

import admin.reporting.decorator.*;

public class ReportingApplication {

    public static void main(String[] args) {

        Report report = new BaseReport();
        Report reportS = new BaseReport();

        report = new FilteredReport(report);
        report = new EncryptedReport(report);
        report = new SignedReport(report);


        System.out.println(report.generate());
        System.out.println(reportS.generate());
    }
}
