import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GradeWindow {

    public void display() {
        Stage window = new Stage();
        window.setTitle("Grade Assignment");

        Label lblStudentId = new Label("Student ID:");
        TextField txtStudentId = new TextField();

        Label lblCourseCode = new Label("Course Code:");
        TextField txtCourseCode = new TextField();

        Label lblGrade = new Label("Grade:");
        TextField txtGrade = new TextField();

        Button btnAssign = new Button("Assign Grade");
        btnAssign.setOnAction(e -> {
            String studentId = txtStudentId.getText();
            String courseCode = txtCourseCode.getText();
            String grade = txtGrade.getText();
            // Add grade assignment save code here
            System.out.println("Assigned Grade: " + grade + " to Student ID: " + studentId + " for Course: " + courseCode);
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(lblStudentId, txtStudentId, lblCourseCode, txtCourseCode, lblGrade, txtGrade, btnAssign);

        Scene scene = new Scene(layout, 350, 300);
        window.setScene(scene);
        window.show();
    }
}
