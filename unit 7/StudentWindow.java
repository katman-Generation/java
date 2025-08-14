import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentWindow {

    public void display() {
        Stage window = new Stage();
        window.setTitle("Student Management");

        Label lblName = new Label("Student Name:");
        TextField txtName = new TextField();

        Label lblId = new Label("Student ID:");
        TextField txtId = new TextField();

        Button btnSave = new Button("Save");
        btnSave.setOnAction(e -> {
            String name = txtName.getText();
            String id = txtId.getText();
            // Here you would add code to save student data to file or list
            System.out.println("Saved Student: " + name + ", ID: " + id);
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(lblName, txtName, lblId, txtId, btnSave);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }
}

