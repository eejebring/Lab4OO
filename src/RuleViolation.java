public class RuleViolation extends Exception {
    private String message;

    RuleViolation (String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
