import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int empId;
    private final String name;
    private final String department;
    private final double salary;

    public Employee(int empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Employee [ID=%d, Name=%s, Department=%s, Salary=₹%.2f]",
                empId, name, department, salary);
    }
}

public class Experiment3 {

    // Serialize a list of Employee objects to a file
    public static void serializeEmployees(String filePath, List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(employees);
            System.out.println("Serialized " + employees.size() + " employee records to " + filePath);
        } catch (IOException e) {
            System.err.println("Serialization failed: " + e.getMessage());
        }
    }

    // Deserialize Employee objects from the file
    @SuppressWarnings("unchecked")
    public static List<Employee> deserializeEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("\n--- Deserialized Employee Records ---");
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Serialized file not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Deserialization failed: " + e.getMessage());
        }
        return employees;
    }

    public static void main(String[] args) {
        String serFile = "employees.ser";

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(501, "Rohan Mehta", "Software Engineering", 95000.00));
        employeeList.add(new Employee(502, "Neha Gupta", "Data Science", 110000.00));
        employeeList.add(new Employee(503, "Vikram Gill", "Cloud Operations", 88500.00));

        serializeEmployees(serFile, employeeList);
        deserializeEmployees(serFile);
    }
}