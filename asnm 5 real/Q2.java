import java.util.NoSuchElementException;
import java.util.Optional;

public class Q2 {
    public static void main(String[] args) {
        // 1. Create an Optional containing an email address
        Optional<String> presentEmail = Optional.of("arjun.sharma@university.edu");

        // 2. Create an empty Optional
        Optional<String> emptyEmail = Optional.empty();

        // 3. Display the email using ifPresent()
        System.out.print("Present email check: ");
        presentEmail.ifPresent(email -> System.out.println("Student Email: " + email));

        System.out.print("Empty email check (ifPresent): ");
        emptyEmail.ifPresent(email -> System.out.println("Student Email: " + email));
        System.out.println("(Nothing printed above because Optional is empty)");

        // 4. Use orElse() to provide a default email address
        String resolvedEmail1 = presentEmail.orElse("not-provided@university.edu");
        String resolvedEmail2 = emptyEmail.orElse("not-provided@university.edu");
        System.out.println("Resolved (present): " + resolvedEmail1);
        System.out.println("Resolved (empty):   " + resolvedEmail2);

        // 5. Use orElseThrow() to handle a missing email value
        try {
            String requiredEmail = emptyEmail.orElseThrow(
                () -> new NoSuchElementException("Error: Student email address is mandatory!")
            );
            System.out.println(requiredEmail);
        } catch (NoSuchElementException e) {
            System.out.println("Exception caught -> " + e.getMessage());
        }
    }
}