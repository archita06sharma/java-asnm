package library.model;

public class Book extends LibraryResource implements Printable {

    public Book(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    // Book fine = Rs. 5 per day
    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 5.0;
    }

    // Implementing Printable
    @Override
    public void printDetails() {
        System.out.println("----- Book Details -----");

        displayBasicDetails();

        System.out.println("Resource Type : Book");
        System.out.println("Fine Rate     : Rs. 5/day");
    }
}