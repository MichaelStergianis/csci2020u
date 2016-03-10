package sample;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by 100558610 on 10-03-2016.
 */
public class UI extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Assignment1");

        TableView/*WordBag*/ table = new TableView();
        table.setEditable(true);

        //file column, unsure about table column identifiers so I put what I thought they should be
        TableColumn/*WordBag, String*/ fileCol = new TableColumn("File");
        fileCol.setMinWidth(500);
        fileCol.setCellValueFactory(new PropertyValueFactory<>("file"));

        //Spam/ham identifier column
        TableColumn/*WordBag, String*/ classCol = new TableColumn("Actual Class");
        classCol.setMinWidth(100);
        classCol.setCellValueFactory(new PropertyValueFactory<>("spamHam"));

        //spam probability column
        TableColumn/*WordBag, Double*/ spamCol = new TableColumn("Spam Probability");
        spamCol.setMinWidth(200);
        spamCol.setCellValueFactory(new PropertyValueFactory<>("spamProb"));

        table.getColumns().addAll(fileCol, classCol, spamCol);


        GridPane infoPane = new GridPane();
        infoPane.setPadding(new Insets(10,10,10,10));
        infoPane.setVgap(10);
        infoPane.setHgap(10);


        //I am unsure of the best way to show the data here, as a label does not really make
        //sense neither does a textfield.
        Label accuracy = new Label("Accuracy:");
        infoPane.add(accuracy, 0 ,0);

        Label precision = new Label("Precision");
        infoPane.add(precision, 0, 1);


        BorderPane layout = new BorderPane();
        layout.setTop(table);
        layout.setBottom(infoPane);
        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
