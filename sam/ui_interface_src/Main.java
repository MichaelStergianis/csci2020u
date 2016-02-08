package uiDemo1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.awt.*;

public class Main extends Application {

    private Label sidLabel;
    private Label fnLabel;
    private Label lnLabel;



    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("JavaFX Demo 1");

        Menu fileMenu = new Menu("File");

        MenuItem newMenuItem = new MenuItem("New"/*, new ImageView(new Image("/home/michael/images/new.png"))*/);
        newMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl-N"));
        fileMenu.getItems().add(newMenuItem);

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem openMenuItem = new MenuItem("Open..." /*, new ImageView(new Image("/home/michael/images/open.png"))*/);
        openMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl-O"));
        fileMenu.getItems().add(openMenuItem);

        MenuItem exitMenuItem = new MenuItem("Exit" /*, new ImageView(new Image("/home/michael/images/exit.png"))*/);
        exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl-Q"));
        exitMenuItem.setOnAction( e -> System.exit(0) );
        fileMenu.getItems().add(exitMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        TableView<Student> table = new TableView<Student>();
        table.setItems(StudentDataSource.getAllStudents());
        table.setEditable(true);

        TableColumn<Student, Integer> sidColumn = new TableColumn<>("SID");
        sidColumn.setMinWidth(100);
        sidColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("sid"));

        TableColumn<Student, String> fnColumn = new TableColumn<>("First Name");
        fnColumn.setMinWidth(200);
        fnColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        fnColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
        fnColumn.setOnEditCommit((TableColumn.CellEditEvent<Student, String> event) -> {
            String newValue = event.getNewValue();
            int index = event.getTablePosition().getRow();
            Student row = event.getTableView().getItems().get(index);
            row.setFirstName(newValue);
        } );

        TableColumn<Student, String> lnColumn = new TableColumn<>("Last Name");
        lnColumn.setMinWidth(200);
        lnColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));

        table.getColumns().add(sidColumn);
        table.getColumns().add(fnColumn);
        table.getColumns().add(lnColumn);

        // form to add new students
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        sidLabel = new Label("SID:");
        grid.add(sidLabel, 0, 0);
        TextField sidField = new TextField();
        sidField.setPromptText("100000000");
        grid.add(sidField, 1, 0); // col, row

        fnLabel = new Label("First Name:");
        grid.add(fnLabel, 0, 1);
        TextField fnField = new TextField();
        fnField.setPromptText("Roberta");
        grid.add(fnField, 1, 1); // col, row

        lnLabel = new Label("Last Name:");
        grid.add(lnLabel, 0, 2);
        TextField lnField = new TextField();
        lnField.setPromptText("Rodriguez");
        grid.add(lnField, 1, 2); // col, row

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e){
                System.out.println(sidField.getText());
                int sid = Integer.parseInt(sidField.getText());
                String firstName = fnField.getText();
                String lastName = lnField.getText();
                double gpa = 0.0;

                Student newStudent = new Student(sid, firstName, lastName, gpa);

                table.getItems().add(newStudent);
            }

        });
        grid.add(addButton, 1, 3);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(table);
        layout.setBottom(grid);


        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}