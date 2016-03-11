package Spam;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        File spamDir = new File("/home/michael/Documents/school/csci2020u/assignments/assignment01/out/production/assignment01/data/train/spam");
        File hamDir = new File("/home/michael/Documents/school/csci2020u/assignments/assignment01/out/production/assignment01/data/train/ham");
        File spamTest = new File("/home/michael/Documents/school/csci2020u/assignments/assignment01/out/production/assignment01/data/test/spam");
        File hamTest = new File("/home/michael/Documents/school/csci2020u/assignments/assignment01/out/production/assignment01/data/test/ham");

        // create and train the filter
        Filter f = new Filter();
        f.trainSpam(spamDir);
        f.trainHam(hamDir);



        ArrayList<TestFile> fileList = new ArrayList<>();
        // test the filter, placing the results into a list
        f.test(spamTest, fileList);
        f.test(hamTest, fileList);

        // Load the fileList into a table to show results

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}