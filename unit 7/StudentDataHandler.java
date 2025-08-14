import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDataHandler {
    private static final String STUDENT_FILE = "students.txt";

    public static void saveStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE, true))) {
            writer.write(student.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing student data: " + e.getMessage());
        }
    }

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENT_FILE);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading student data: " + e.getMessage());
        }

        return students;
    }
}
