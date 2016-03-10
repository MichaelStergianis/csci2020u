package Spam;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.activation.DataSource;
import java.io.File;
import java.util.ArrayList;

public class Main extends Application {
    private BorderPane layout;
    private TableView<TestFile> table;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Directory Picker to let user navigate to data folder
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Data Directory");
        directoryChooser.setInitialDirectory(new File("."));
        File mainDirectory = directoryChooser.showDialog(primaryStage);

        // Adds extentions to the mainDirectory to navigate to respective folders
        File spamDir = new File(mainDirectory + "/train/spam");
        File hamDir = new File(mainDirectory + "/train/ham");
        File spamTest = new File(mainDirectory + "/test/spam");
        File hamTest = new File(mainDirectory + "/test/ham");

        // create and train the filter
        Filter f = new Filter();
        f.trainSpam(spamDir);
        f.trainHam(hamDir);

        // create proper variable for contents of list
        ObservableList<TestFile> fileList = FXCollections.observableArrayList();

        // test the filter, placing the results into a list
        f.test(spamTest, fileList);
        f.test(hamTest, fileList);

        // Load the fileList into a table to show results
        table = new TableView<>();
        table.setItems(fileList);

        // Table columns
        TableColumn<TestFile,String> fileColumn;
        fileColumn = new TableColumn<>("File");
        fileColumn.setMinWidth(300);
        fileColumn.setCellValueFactory(new PropertyValueFactory<>("Filename"));

        TableColumn<TestFile,String> classColumn;
        classColumn = new TableColumn<>("Actual Class");
        classColumn.setMinWidth(200);
        classColumn.setCellValueFactory(new PropertyValueFactory<>("ActualClass"));

        TableColumn<TestFile,String> spamColumn;
        spamColumn = new TableColumn<>("Spam Probability");
        spamColumn.setMinWidth(300);
        spamColumn.setCellValueFactory(new PropertyValueFactory<>("SpamProbability"));

        // Table Columns
        table.getColumns().add(fileColumn);
        table.getColumns().add(classColumn);
        table.getColumns().add(spamColumn);

        // Display Area Below Table
        GridPane editArea = new GridPane();
        editArea.setPadding(new Insets(10, 10, 10, 10));
        editArea.setVgap(10);
        editArea.setHgap(10);

        // FIX VARIABLES x and y
        // convert probability into string and then replace x and y respectively
        String accuracy = "Accuracy Variable";
        String precision = "Precision Variable";


        // Area to show accuracy and probability below table
        Label accuracyLabel = new Label("Accuracy:");
        editArea.add(accuracyLabel, 0, 0);
        TextField accuracyField = new TextField();
        accuracyField.setEditable(false);
        accuracyField.setText(accuracy);
        editArea.add(accuracyField, 1, 0);

        Label precisionLabel = new Label("Precision:");
        editArea.add(precisionLabel, 0, 1);
        TextField precisionField = new TextField();
        precisionField.setEditable(false);
        precisionField.setText(precision);
        editArea.add(precisionField, 1, 1);

        // Arrangement of components in the spam detector UI
        layout = new BorderPane();
        layout.setCenter(table);
        layout.setBottom(editArea);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Spam Master 3000");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}