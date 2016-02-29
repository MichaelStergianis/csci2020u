package lab05;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Record Display");

        TableView<StudentRecord> table = new TableView<StudentRecord>();
        table.setItems(DataSource.getAllMarks());
        table.setEditable(true);

        // SID column
        TableColumn<StudentRecord, String> sidColumn = new TableColumn<>("SID");
        sidColumn.setMinWidth(100);
        sidColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("studentID"));

        // Midterm column
        TableColumn<StudentRecord, Double> midtermColumn = new TableColumn<>("Midterm");
        midtermColumn.setMinWidth(100);
        midtermColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Double>("midterm"));

        // Assignments column
        TableColumn<StudentRecord, String> assignmentColumn = new TableColumn<>("Assignments");
        assignmentColumn.setMinWidth(120);
        assignmentColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("assignments"));

        // Final Exam column
        TableColumn<StudentRecord, String> finalExamColumn = new TableColumn<>("Final Exam");
        finalExamColumn.setMinWidth(100);
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("finalExam"));

        // Final Mark column
        TableColumn<StudentRecord, String> finalMarkColumn = new TableColumn<>("Final Mark");
        finalMarkColumn.setMinWidth(100);
        finalMarkColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("finalMark"));

        // Letter Grade column
        TableColumn<StudentRecord, String> letterGradeColumn = new TableColumn<>("Letter Grade");
        letterGradeColumn.setMinWidth(100);
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("letterGrade"));

        // add columns
        table.getColumns().add(sidColumn);
        table.getColumns().add(midtermColumn);
        table.getColumns().add(assignmentColumn);
        table.getColumns().add(finalExamColumn);
        table.getColumns().add(finalMarkColumn);
        table.getColumns().add(letterGradeColumn);


        // create the border pane
        BorderPane layout = new BorderPane();
        layout.setCenter(table);


        primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
