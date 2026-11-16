import java.io.*;

public class Experiment2 {

    // Helper method to generate a sample log file for testing
    private static void createSampleLogFile(String logPath) {
        String[] sampleLogs = {
            "2026-11-17 09:00:01 INFO  Application started successfully.",
            "2026-11-17 09:05:14 INFO  User 'admin' logged in.",
            "2026-11-17 09:12:45 WARNING Disk usage exceeded 80% threshold.",
            "2026-11-17 09:18:22 ERROR Database connection timeout on port 5432.",
            "2026-11-17 09:20:00 INFO  Retrying database connection...",
            "2026-11-17 09:25:10 WARNING Slow response detected in /api/v1/orders.",
            "2026-11-17 09:30:55 ERROR NullPointerException in PaymentProcessor.java:84"
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logPath))) {
            for (String line : sampleLogs) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error creating sample log file: " + e.getMessage());
        }
    }

    public static void analyzeLogs(String inputLogPath, String outputReportPath) {
        int totalEntries = 0;
        int errorCount = 0;
        int warningCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputLogPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputReportPath))) {

            writer.write("=== FILTERED LOG REPORT (ERRORS & WARNINGS) ===");
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                totalEntries++;

                boolean isError = line.contains("ERROR");
                boolean isWarning = line.contains("WARNING");

                if (isError || isWarning) {
                    if (isError) errorCount++;
                    if (isWarning) warningCount++;

                    writer.write(line);
                    writer.newLine();
                }
            }

            writer.newLine();
            writer.write("=== SUMMARY ===");
            writer.newLine();
            writer.write("Total Log Entries Processed : " + totalEntries);
            writer.newLine();
            writer.write("Total ERROR Entries         : " + errorCount);
            writer.newLine();
            writer.write("Total WARNING Entries       : " + warningCount);
            writer.newLine();

            System.out.println("Log analysis complete!");
            System.out.println("Total Entries : " + totalEntries);
            System.out.println("Errors Found  : " + errorCount);
            System.out.println("Warnings Found: " + warningCount);
            System.out.println("Filtered results saved to: " + outputReportPath);

        } catch (FileNotFoundException e) {
            System.err.println("Log file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error while processing logs: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logFile = "application.log";
        String outputFile = "filtered_logs.txt";

        createSampleLogFile(logFile);
        analyzeLogs(logFile, outputFile);
    }
}