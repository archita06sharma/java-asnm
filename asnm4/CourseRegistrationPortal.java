import java.util.*;

class Course {
    private final String courseCode;
    private final String title;
    private final int capacity;
    private final HashSet<String> enrolledStudents;
    private final ArrayList<String> prerequisites;
    private final Queue<String> waitlist;

    public Course(String courseCode, String title, int capacity, List<String> prerequisites) {
        this.courseCode = courseCode;
        this.title = title;
        this.capacity = capacity;
        this.enrolledStudents = new HashSet<>();
        this.prerequisites = new ArrayList<>(prerequisites != null ? prerequisites : Collections.emptyList());
        this.waitlist = new LinkedList<>();
    }

    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public int getCapacity() { return capacity; }
    public Set<String> getEnrolledStudents() { return enrolledStudents; }
    public List<String> getPrerequisites() { return prerequisites; }
    public Queue<String> getWaitlist() { return waitlist; }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s] Enrolled: %d/%d | Waitlist: %d | Prereqs: %s",
                courseCode, title, enrolledStudents.size(), capacity, waitlist.size(), prerequisites);
    }
}

public class CourseRegistrationPortal {
    // Fast O(1) lookup of courses by course code
    private final HashMap<String, Course> courseCatalog = new HashMap<>();
    
    // Tracks completed courses per student to verify prerequisites
    private final HashMap<String, Set<String>> studentCompletedCourses = new HashMap<>();

    public void addCourse(Course course) {
        courseCatalog.put(course.getCourseCode(), course);
    }

    public void recordCompletedCourse(String studentId, String courseCode) {
        studentCompletedCourses
                .computeIfAbsent(studentId, k -> new HashSet<>())
                .add(courseCode);
    }

    public void registerStudent(String studentId, String courseCode) {
        Course course = courseCatalog.get(courseCode);
        if (course == null) {
            System.out.println("Error: Course " + courseCode + " not found.");
            return;
        }

        // 1. Check if already enrolled or waitlisted
        if (course.getEnrolledStudents().contains(studentId)) {
            System.out.println(studentId + " is already enrolled in " + courseCode + ".");
            return;
        }
        if (course.getWaitlist().contains(studentId)) {
            System.out.println(studentId + " is already on the waitlist for " + courseCode + ".");
            return;
        }

        // 2. Verify ordered prerequisites
        Set<String> completed = studentCompletedCourses.getOrDefault(studentId, Collections.emptySet());
        for (String prereq : course.getPrerequisites()) {
            if (!completed.contains(prereq)) {
                System.out.println("Registration denied for " + studentId + " in " + courseCode +
                        ": Missing prerequisite (" + prereq + ").");
                return;
            }
        }

        // 3. Capacity check and waitlist assignment
        if (!course.isFull()) {
            course.getEnrolledStudents().add(studentId);
            System.out.println("Success: " + studentId + " enrolled in " + courseCode + ".");
        } else {
            course.getWaitlist().offer(studentId);
            System.out.println("Course full: " + studentId + " added to waitlist for " + courseCode +
                    " (Position: " + course.getWaitlist().size() + ").");
        }
    }

    public void dropStudent(String studentId, String courseCode) {
        Course course = courseCatalog.get(courseCode);
        if (course == null) {
            System.out.println("Error: Course " + courseCode + " not found.");
            return;
        }

        // If student was enrolled, remove and trigger FIFO waitlist promotion
        if (course.getEnrolledStudents().remove(studentId)) {
            System.out.println("Dropped: " + studentId + " removed from " + courseCode + ".");
            promoteFromWaitlist(course);
        } else if (course.getWaitlist().remove(studentId)) {
            System.out.println("Removed: " + studentId + " left the waitlist for " + courseCode + ".");
        } else {
            System.out.println(studentId + " is not associated with " + courseCode + ".");
        }
    }

    private void promoteFromWaitlist(Course course) {
        if (!course.isFull() && !course.getWaitlist().isEmpty()) {
            String nextStudent = course.getWaitlist().poll(); // FIFO dequeue
            course.getEnrolledStudents().add(nextStudent);
            System.out.println("Auto-Promoted: " + nextStudent + " moved from waitlist into " + course.getCourseCode() + ".");
        }
    }

    public void printCatalogStatus() {
        System.out.println("\n--- Current Course Catalog Status ---");
        for (Course c : courseCatalog.values()) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------\n");
    }

    public static void main(String[] args) {
        CourseRegistrationPortal portal = new CourseRegistrationPortal();

        // Setup courses
        portal.addCourse(new Course("CS101", "Intro to Programming", 2, null));
        portal.addCourse(new Course("CS201", "Data Structures", 2, Arrays.asList("CS101")));
        portal.addCourse(new Course("CS301", "Algorithms", 2, Arrays.asList("CS101", "CS201")));

        // Record completed courses for students
        portal.recordCompletedCourse("STU01", "CS101");
        portal.recordCompletedCourse("STU02", "CS101");
        portal.recordCompletedCourse("STU03", "CS101");
        portal.recordCompletedCourse("STU04", "CS101");
        // STU05 has NOT completed CS101

        // Test Prerequisite check
        portal.registerStudent("STU05", "CS201"); // Fails: missing CS101

        // Test Capacity & Waitlist queuing
        portal.registerStudent("STU01", "CS201"); // Enrolled (1/2)
        portal.registerStudent("STU02", "CS201"); // Enrolled (2/2)
        portal.registerStudent("STU03", "CS201"); // Waitlist #1
        portal.registerStudent("STU04", "CS201"); // Waitlist #2

        portal.printCatalogStatus();

        // Test Drop & Automated FIFO Promotion
        portal.dropStudent("STU01", "CS201");     // STU03 gets auto-promoted

        portal.printCatalogStatus();
    }
}