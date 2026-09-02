package library.model;

public class DigitalResource extends LibraryResource implements Printable {

    public DigitalResource(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    // Digital resource fine = Rs. 2 per day
    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 2.0;
    }

    // Implementing Printable
    @Override
    public void printDetails() {
        System.out.println("----- Digital Resource Details -----");

        displayBasicDetails();

        System.out.println("Resource Type : Digital Resource");
        System.out.println("Fine Rate     : Rs. 2/day");
    }
}