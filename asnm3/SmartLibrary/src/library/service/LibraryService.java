package library.service;

import library.model.LibraryResource;
import library.model.Printable;

public class LibraryService {

    // Display all resources
    public void displayResources(LibraryResource[] resources) {

        for (LibraryResource resource : resources) {

            if (resource instanceof Printable) {
                Printable printable = (Printable) resource;
                printable.printDetails();
            }

            System.out.println();
        }
    }

    // Calculate total fine
    public double calculateTotalFine(
            LibraryResource[] resources,
            int[] overdueDays) {

        double totalFine = 0;

        for (int i = 0; i < resources.length; i++) {

            totalFine += resources[i].calculateFine(overdueDays[i]);
        }

        return totalFine;
    }
}