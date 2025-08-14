import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseWindow {

    public void display() {
        Stage window = new Stage();
        window.setTitle("Course Enrollment");

        Label lblCourseName = new Label("Course Name:");
        TextField txtCourseName = new TextField();

        Label lblCourseCode = new Label("Course Code:");
        TextField txtCourseCode = new TextField();

        Button btnEnroll = new Button("Enroll");
        btnEnroll.setOnAction(e -> {
            String courseName = txtCourseName.getText();
            String courseCode = txtCourseCode.getText();
            // Add enrollment save code here
            System.out.println("Enrolled in Course: " + courseName + ", Code: " + courseCode);
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(lblCourseName, txtCourseName, lblCourseCode, txtCourseCode, btnEnroll);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }
}
