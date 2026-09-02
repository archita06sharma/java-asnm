package library.util;

public class InputValidator {

    // Private constructor so objects cannot be created
    private InputValidator() {
    }

    // Validate Resource ID
    public static boolean isValidResourceId(int resourceId) {
        return resourceId > 0;
    }

    // Validate Fine Days
    public static boolean isValidFineDays(int overdueDays) {
        return overdueDays >= 0;
    }
}