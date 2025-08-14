import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btnStudentWindow = new Button("Student Management");
        Button btnCourseWindow = new Button("Course Enrollment");
        Button btnGradeWindow = new Button("Grade Assignment");

        btnStudentWindow.setOnAction(e -> new StudentWindow().display());
        btnCourseWindow.setOnAction(e -> new CourseWindow().display());
        btnGradeWindow.setOnAction(e -> new GradeWindow().display());

        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(btnStudentWindow, btnCourseWindow, btnGradeWindow);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
