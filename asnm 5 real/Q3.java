import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {
    private final String studentName;
    private final T[] marks;
    private final double maxMarksPerSubject;

    public Result(String studentName, T[] marks, double maxMarksPerSubject) {
        // 5. Validate required inputs using Objects.requireNonNull()
        this.studentName = Objects.requireNonNull(studentName, "Student name cannot be null");
        this.marks = marks; // Allowed to be null so Optional can handle missing marks
        if (maxMarksPerSubject <= 0) {
            throw new IllegalArgumentException("Max marks per subject must be greater than zero");
        }
        this.maxMarksPerSubject = maxMarksPerSubject;
    }

    // 2. Calculate percentage using numeric conversion methods
    public Optional<Double> calculatePercentage() {
        if (marks == null || marks.length == 0) {
            return Optional.empty();
        }
        double totalObtained = 0.0;
        for (T mark : marks) {
            Objects.requireNonNull(mark, "Individual mark entry cannot be null");
            totalObtained += mark.doubleValue();
        }
        double totalMax = marks.length * maxMarksPerSubject;
        return Optional.of((totalObtained / totalMax) * 100.0);
    }

    // 3. Determine pass/fail based on threshold
    public boolean hasPassed(double passPercentageThreshold) {
        return calculatePercentage()
                .map(pct -> pct >= passPercentageThreshold)
                .orElse(false);
    }

    // 4. Display result using Optional if marks are available
    public void displayResult(double passThreshold) {
        System.out.println("Student: " + studentName);
        calculatePercentage().ifPresentOrElse(
            pct -> {
                String status = hasPassed(passThreshold) ? "PASSED" : "FAILED";
                System.out.printf("Percentage: %.2f%% | Status: %s (Threshold: %.1f%%)%n",
                        pct, status, passThreshold);
            },
            () -> System.out.println("Result unavailable: No marks recorded.")
        );
        System.out.println("--------------------------------------------------");
    }
}

public class Q3 {
    public static void main(String[] args) {
        Integer[] priyaMarks = {85, 90, 78, 88};
        Result<Integer> r1 = new Result<>("Priya Verma", priyaMarks, 100.0);
        r1.displayResult(40.0);

        Double[] rohanMarks = {32.5, 38.0, 29.5};
        Result<Double> r2 = new Result<>("Rohan Nair", rohanMarks, 100.0);
        r2.displayResult(40.0);

        // Testing missing marks handled via Optional
        Result<Integer> r3 = new Result<>("Ananya Das", null, 100.0);
        r3.displayResult(40.0);
    }
}