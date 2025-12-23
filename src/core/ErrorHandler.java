package core;

public class ErrorHandler {
    public void handle(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }
}