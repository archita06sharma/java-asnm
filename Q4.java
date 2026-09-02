import java.util.Scanner;

class InvalidMarksException extends Exception {

    public InvalidMarksException(String message) {
        super(message);
    }
}

public class Q4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter number of subjects: ");
            int n = sc.nextInt();

            int total = 0;

            for (int i = 1; i <= n; i++) {

                System.out.print("Enter marks for subject " + i + ": ");
                int marks = sc.nextInt();

                if (marks < 0 || marks > 100) {
                    throw new InvalidMarksException(
                            "Invalid marks: " + marks +
                            ". Marks must be between 0 and 100."
                    );
                }

                total += marks;
            }

            double percentage = (double) total / n;

            System.out.println("\n----- RESULT -----");
            System.out.println("Total Marks = " + total);
            System.out.println("Percentage = " + percentage + "%");

            if (percentage >= 90) {
                System.out.println("Grade = A+");
            } else if (percentage >= 80) {
                System.out.println("Grade = A");
            } else if (percentage >= 70) {
                System.out.println("Grade = B");
            } else if (percentage >= 60) {
                System.out.println("Grade = C");
            } else if (percentage >= 50) {
                System.out.println("Grade = D");
            } else {
                System.out.println("Grade = F");
            }

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Error: Invalid input.");

        } finally {
            sc.close();
        }
    }
}