package library.main;

import library.model.Book;
import library.model.DigitalResource;
import library.model.LibraryResource;
import library.service.LibraryService;
import library.util.InputValidator;

public class LibraryMain {

    public static void main(String[] args) {

        // Create at least five objects

        Book book1 = new Book(
                101,
                "Data Structures and Algorithms",
                "Narasimha Karumanchi"
        );

        Book book2 = new Book(
                102,
                "Clean Code",
                "Robert C. Martin"
        );

        Book book3 = new Book(
                103,
                "Operating System Concepts",
                "Abraham Silberschatz"
        );

        DigitalResource digital1 = new DigitalResource(
                201,
                "Java Programming Course",
                "Oracle"
        );

        DigitalResource digital2 = new DigitalResource(
                202,
                "Machine Learning Fundamentals",
                "Andrew Ng"
        );

        // Validate resource IDs
        if (!InputValidator.isValidResourceId(book1.getResourceId()) ||
            !InputValidator.isValidResourceId(book2.getResourceId()) ||
            !InputValidator.isValidResourceId(book3.getResourceId()) ||
            !InputValidator.isValidResourceId(digital1.getResourceId()) ||
            !InputValidator.isValidResourceId(digital2.getResourceId())) {

            System.out.println("Invalid Resource ID!");
            return;
        }

        // Store objects in an array
        LibraryResource[] resources = {
                book1,
                book2,
                book3,
                digital1,
                digital2
        };

        // Overdue days for each resource
        int[] overdueDays = {
                3,  // Book 1
                5,  // Book 2
                0,  // Book 3
                4,  // Digital 1
                2   // Digital 2
        };

        // Validate overdue days
        for (int days : overdueDays) {

            if (!InputValidator.isValidFineDays(days)) {
                System.out.println("Invalid overdue days!");
                return;
            }
        }

        // Create service object
        LibraryService service = new LibraryService();

        // Display library name
        System.out.println("======================================");
        System.out.println("     " + LibraryResource.libraryName);
        System.out.println("======================================");
        System.out.println();

        // Display complete details
        service.displayResources(resources);

        // Calculate total fine
        double totalFine =
                service.calculateTotalFine(resources, overdueDays);

        System.out.println("======================================");
        System.out.println("Total Fine : Rs. " + totalFine);
        System.out.println("======================================");

        // Display total resources created
        LibraryResource.displayTotalResources();
    }
}