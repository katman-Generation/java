import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDataHandler {
    private static final String COURSE_FILE = "courses.txt";

    public static void saveCourse(Course course) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSE_FILE, true))) {
            writer.write(course.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing course data: " + e.getMessage());
        }
    }

    public static List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        File file = new File(COURSE_FILE);

        if (!file.exists()) {
            return courses;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    courses.add(new Course(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading course data: " + e.getMessage());
        }

        return courses;
    }
}
