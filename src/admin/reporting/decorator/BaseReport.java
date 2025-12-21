package admin.reporting.decorator;

public class BaseReport implements Report {

    @Override
    public String generate() {
        return "Base Report Data";
    }
}
