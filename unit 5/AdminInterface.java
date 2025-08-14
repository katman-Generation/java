import java.util.*;

// Student class encapsulates student information and course enrollments
class Student {
    private String studentName;
    private String studentID;
    private Map<Course, Double> registeredCourses = new HashMap<>();

    public Student(String studentName, String studentID) {
        this.studentName = studentName;
        this.studentID = studentID;
    }

    // Getters and Setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String newName) {
        this.studentName = newName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String newID) {
        this.studentID = newID;
    }

    public Map<Course, Double> getRegisteredCourses() {
        return registeredCourses;
    }

    // Method to enroll student in a course
    public boolean registerForCourse(Course newCourse) {
        if (newCourse.getCurrentEnrollment() < newCourse.getMaximumCapacity()) {
            registeredCourses.put(newCourse, null);
            newCourse.incrementEnrollment();
            return true;
        } else {
            return false;
        }
    }

    // Method to assign grade
    public void updateCourseGrade(Course course, double gradeScore) {
        if (registeredCourses.containsKey(course)) {
            registeredCourses.put(course, gradeScore);
        }
    }
}

// Course class encapsulates course details and static tracking
class Course {
    private String courseCode;
    private String courseName;
    private int maximumCapacity;
    private int currentEnrollment;

    private static int globalEnrollmentCount = 0;

    public Course(String code, String name, int maxCap) {
        this.courseCode = code;
        this.courseName = name;
        this.maximumCapacity = maxCap;
        this.currentEnrollment = 0;
    }

    // Getters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    // Increase enrollment
    public void incrementEnrollment() {
        currentEnrollment++;
        globalEnrollmentCount++;
    }

    // Static method to get total enrollment
    public static int getTotalEnrolledStudents() {
        return globalEnrollmentCount;
    }
}

// CourseManagement class handles admin operations using static members
class CourseManagement {
    private static List<Course> courseCatalog = new ArrayList<>();
    private static Map<Student, Double> finalGrades = new HashMap<>();

    public static void addNewCourse(String code, String name, int capacity) {
        courseCatalog.add(new Course(code, name, capacity));
    }

    public static List<Course> getCourseCatalog() {
        return courseCatalog;
    }

    public static boolean enrollStudentInCourse(Student learner, Course subject) {
        return learner.registerForCourse(subject);
    }

    public static void assignGradeToStudent(Student learner, Course subject, double score) {
        learner.updateCourseGrade(subject, score);
    }

    public static double calculateFinalGrade(Student learner) {
        Map<Course, Double> courseScores = learner.getRegisteredCourses();
        double sum = 0;
        int count = 0;

        for (Double score : courseScores.values()) {
            if (score != null) {
                sum += score;
                count++;
            }
        }

        double average = count > 0 ? sum / count : 0;
        finalGrades.put(learner, average);
        return average;
    }
}

// AdminInterface provides a command-line interface for course management
public class AdminInterface {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        List<Student> studentList = new ArrayList<>();

        while (true) {
            System.out.println("\n--- University Course Management ---");
            System.out.println("1. Add New Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign Grade");
            System.out.println("4. Calculate Final Grade");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = userInput.nextInt();
            userInput.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Course Code: ");
                    String cCode = userInput.nextLine();
                    System.out.print("Course Name: ");
                    String cName = userInput.nextLine();
                    System.out.print("Max Capacity: ");
                    int cap = userInput.nextInt();
                    CourseManagement.addNewCourse(cCode, cName, cap);
                    System.out.println("Course added successfully.");
                    break;

                case 2:
                    System.out.print("Student Name: ");
                    String sName = userInput.nextLine();
                    System.out.print("Student ID: ");
                    String sID = userInput.nextLine();
                    Student student = new Student(sName, sID);
                    studentList.add(student);

                    System.out.println("Available Courses:");
                    List<Course> catalog = CourseManagement.getCourseCatalog();
                    for (int i = 0; i < catalog.size(); i++) {
                        Course course = catalog.get(i);
                        System.out.printf("%d. %s (%s)\n", i + 1, course.getCourseName(), course.getCourseCode());
                    }

                    System.out.print("Select Course Number to Enroll: ");
                    int courseIndex = userInput.nextInt() - 1;

                    if (courseIndex >= 0 && courseIndex < catalog.size()) {
                        Course selected = catalog.get(courseIndex);
                        boolean success = CourseManagement.enrollStudentInCourse(student, selected);
                        if (success) {
                            System.out.println("Student enrolled successfully.");
                        } else {
                            System.out.println("Course is full.");
                        }
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                    break;

                case 3:
                    if (studentList.isEmpty()) {
                        System.out.println("No students enrolled.");
                        break;
                    }

                    System.out.print("Enter Student ID: ");
                    String gradeID = userInput.nextLine();
                    Student foundStudent = null;
                    for (Student s : studentList) {
                        if (s.getStudentID().equals(gradeID)) {
                            foundStudent = s;
                            break;
                        }
                    }

                    if (foundStudent == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    List<Course> enrolledCourses = new ArrayList<>(foundStudent.getRegisteredCourses().keySet());
                    if (enrolledCourses.isEmpty()) {
                        System.out.println("No courses registered for this student.");
                        break;
                    }

                    System.out.println("Enrolled Courses:");
                    for (int i = 0; i < enrolledCourses.size(); i++) {
                        Course c = enrolledCourses.get(i);
                        System.out.printf("%d. %s (%s)\n", i + 1, c.getCourseName(), c.getCourseCode());
                    }

                    System.out.print("Select Course Number to Assign Grade: ");
                    int courseIdx = userInput.nextInt() - 1;

                    if (courseIdx >= 0 && courseIdx < enrolledCourses.size()) {
                        Course chosen = enrolledCourses.get(courseIdx);
                        System.out.print("Enter Grade (0 - 100): ");
                        double grade = userInput.nextDouble();
                        CourseManagement.assignGradeToStudent(foundStudent, chosen, grade);
                        System.out.println("Grade assigned successfully.");
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID to Calculate Final Grade: ");
                    String calcID = userInput.nextLine();
                    Student match = null;
                    for (Student s : studentList) {
                        if (s.getStudentID().equals(calcID)) {
                            match = s;
                            break;
                        }
                    }

                    if (match == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    double finalGrade = CourseManagement.calculateFinalGrade(match);
                    System.out.printf("Final Grade for %s: %.2f\n", match.getStudentName(), finalGrade);
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    userInput.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}

