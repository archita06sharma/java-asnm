import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Accept event name, date, start time, and optional venue
        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine().trim();

        System.out.print("Enter Event Date (YYYY-MM-DD): ");
        LocalDate eventDate = LocalDate.parse(scanner.nextLine().trim());

        System.out.print("Enter Start Time (HH:MM, 24-hour format): ");
        LocalTime startTime = LocalTime.parse(scanner.nextLine().trim());

        System.out.print("Enter Venue (leave blank if undecided): ");
        String venueInput = scanner.nextLine().trim();
        Optional<String> venue = venueInput.isEmpty() ? Optional.empty() : Optional.of(venueInput);

        // 5. Calculate end time (+2 hours)
        LocalTime endTime = startTime.plusHours(2);

        // 2. Display event details
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println("\n=== Event Details ===");
        System.out.println("Event:      " + eventName);
        System.out.println("Date:       " + eventDate);
        System.out.println("Start Time: " + startTime.format(timeFmt));
        System.out.println("End Time:   " + endTime.format(timeFmt) + " (2 hours duration)");

        // 6. Optional venue handling
        System.out.println("Venue:      " + venue.orElse("Venue not specified"));

        // 3 & 4. Check timeline status and calculate remaining days
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(today, eventDate);

        if (eventDate.isAfter(today)) {
            System.out.println("Status:     Upcoming event (in the future)");
            System.out.println("Countdown:  " + daysBetween + " day(s) remaining");
        } else if (eventDate.isEqual(today)) {
            System.out.println("Status:     The event is TODAY!");
            System.out.println("Countdown:  0 days remaining");
        } else {
            System.out.println("Status:     Past event");
            System.out.println("Info:       Occurred " + Math.abs(daysBetween) + " day(s) ago");
        }

        scanner.close();
    }
}