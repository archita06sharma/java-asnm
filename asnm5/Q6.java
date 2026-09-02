class InvalidUsernameException extends Exception {

    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {

    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {

    public AccountLockedException(String message) {
        super(message);
    }
}

class LoginSystem {

    private String correctUsername = "admin";
    private String correctPassword = "admin123";

    private int failedAttempts = 0;
    private final int MAX_ATTEMPTS = 3;
    private boolean locked = false;

    public void login(String username, String password)
            throws InvalidUsernameException,
                   InvalidPasswordException,
                   AccountLockedException {

        if (locked) {
            throw new AccountLockedException(
                    "Account is locked due to too many failed attempts."
            );
        }

        if (!username.equals(correctUsername)) {

            failedAttempts++;

            if (failedAttempts >= MAX_ATTEMPTS) {
                locked = true;
                throw new AccountLockedException(
                        "Account locked after maximum failed attempts."
                );
            }

            throw new InvalidUsernameException(
                    "Invalid username."
            );
        }

        if (!password.equals(correctPassword)) {

            failedAttempts++;

            if (failedAttempts >= MAX_ATTEMPTS) {
                locked = true;
                throw new AccountLockedException(
                        "Account locked after maximum failed attempts."
                );
            }

            throw new InvalidPasswordException(
                    "Invalid password."
            );
        }

        failedAttempts = 0;

        System.out.println("Login successful!");
    }
}

public class Q6 {

    public static void main(String[] args) {

        LoginSystem system = new LoginSystem();

        String username = "admin";
        String password = "wrong";

        for (int attempt = 1; attempt <= 4; attempt++) {

            try {

                System.out.println("\nLogin attempt " + attempt);

                system.login(username, password);

                break;

            } catch (InvalidUsernameException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (InvalidPasswordException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (AccountLockedException e) {

                System.out.println("Error: " + e.getMessage());
                break;

            } finally {

                System.out.println("Login attempt completed.");
            }
        }
    }
}