import java.io.*;

public class Experiment1 {

    // Write student details using character stream (FileWriter)
    public static void writeStudentRecords(String filePath, String[] records) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String record : records) {
                writer.write(record + System.lineSeparator());
            }
            System.out.println("Student records successfully written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing student records: " + e.getMessage());
        }
    }

    // Read student details using character stream (FileReader)
    public static void readStudentRecords(String filePath) {
        System.out.println("\n--- Reading Student Records from " + filePath + " ---");
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Text file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading student records: " + e.getMessage());
        }
    }

    // Copy a binary file using byte streams (FileInputStream & FileOutputStream)
    public static void copyBinaryFile(String sourcePath, String destinationPath) {
        try (FileInputStream fis = new FileInputStream(sourcePath);
             FileOutputStream fos = new FileOutputStream(destinationPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("\nBinary file copied successfully from " + sourcePath + " to " + destinationPath);
        } catch (FileNotFoundException e) {
            System.err.println("Binary file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error during binary file copy: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String textFile = "students.txt";
        String sourceBinary = "student_id_card.bin";
        String copiedBinary = "student_id_card_backup.bin";

        // 1. Character Stream Operations
        String[] studentData = {
            "ID: 101 | Name: Aarav Sharma | Course: B.Tech CSE | CGPA: 8.9",
            "ID: 102 | Name: Simran Kaur  | Course: B.Tech ECE | CGPA: 9.2",
            "ID: 103 | Name: Kabir Verma  | Course: BCA        | CGPA: 8.4"
        };
        writeStudentRecords(textFile, studentData);
        readStudentRecords(textFile);

        // 2. Create a sample binary file, then copy it using Byte Streams
        try (FileOutputStream sampleOut = new FileOutputStream(sourceBinary)) {
            byte[] rawBytes = {0x4A, 0x41, 0x56, 0x41, 0x00, 0x01, 0x02, (byte) 0xFF};
            sampleOut.write(rawBytes);
        } catch (IOException e) {
            System.err.println("Failed to create sample binary file: " + e.getMessage());
        }

        copyBinaryFile(sourceBinary, copiedBinary);
    }
}