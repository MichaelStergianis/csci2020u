package Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;

import java.io.File;

public class UI extends Application {
    private BorderPane layout;
    private String iNet;
    private int port;
    private File sharedFolder;
    private TableView<FileListing> table1 = new TableView();
    private TableView<FileListing> table2 = new TableView();

//    public UI(String iNet, int port){
//        this.setiNet(iNet);
//        this.setPort(port);
//        if (sharedFolder == null || !sharedFolder.isDirectory()) {
//            showError("Cannot share that folder", "",
//                    "Folder was not chosen or is not a directory");
//            System.out.println("Shared Folder not Specified");
//            System.exit(0);
//        }
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("File Transfer Client");

        // Choose inet adress and port
        iNetSelectionDialogue();

//         Choose shared folder
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Select Shared Directory");
        dc.setInitialDirectory(new File("."));
        sharedFolder = dc.showDialog(primaryStage);
        if (sharedFolder == null){
            System.exit(0);
        }

        // Create our datasource
        Datasource ds = new Datasource();

        //SPLIT PANE CONFIG
        SplitPane splitPane1 = new SplitPane();
        splitPane1.setPrefSize(500, 200);

        // Connect to our server
        ConnectionHandler handler = new ConnectionHandler(iNet, port, sharedFolder);
        String[] dirs = handler.receiveDirs();
        ds.updateServerFiles(dirs);
        ds.updateClientFiles(sharedFolder.list());

        //TABLE PROPERTIES
        table1.setItems(ds.getClientFiles());
        table2.setItems(ds.getServerFiles());

        TableColumn clientColumn = null;
        clientColumn = new TableColumn("Client Content");
        clientColumn.setMinWidth(300);
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("FileName"));

        TableColumn serverColumn = null;
        serverColumn = new TableColumn("Server Content");
        serverColumn.setMinWidth(300);
        serverColumn.setCellValueFactory(new PropertyValueFactory<>("FileName"));

        //POPULATING TABLES
        table1.getColumns().add(clientColumn);
        table2.getColumns().add(serverColumn);

        //ADDING TABLE TO SPLIT PANE
        splitPane1.getItems().addAll(table1, table2);

        //BUTTON FUNCTIONALITY
        GridPane functionArea = new GridPane();
        Button up = new Button("Upload");
        up.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent upload) {
                //BUTTON FUNCTIONALITY
                //UPLOADS SELECTED FILE
                FileListing selected = table1.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    handler.send(sharedFolder, selected.getFileName());
                    ds.updateClientFiles(sharedFolder.list());
                    ds.updateServerFiles(handler.receiveDirs());
                }
            }
        });

        Button down = new Button("Download");
        down.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent download) {
                //BUTTON FUNCTIONALITY
                //DOWNLOAD SELECTED FILE
                FileListing selected = table2.getSelectionModel().getSelectedItem();
                if (selected != null){
                    handler.receive(selected.getFileName());
                    ds.updateClientFiles(sharedFolder.list());
                    ds.updateServerFiles(handler.receiveDirs());
                }
            }
        });

        functionArea.add(up, 1, 1);
        functionArea.add(down, 3, 1);

        layout = new BorderPane();
        layout.setTop(functionArea);
        layout.setCenter(splitPane1);

        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void iNetSelectionDialogue(){
        Stage iNetStage = new Stage();
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, 500, 150);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label iNetLabel = new Label("Network adress or url");
        grid.add(iNetLabel, 0, 0);

        TextField iNetField = new TextField();
        iNetField.setPrefWidth(150);
        iNetField.setPromptText("127.0.0.1 or linux.org");
        grid.add(iNetField, 1, 0);

        Label portLabel = new Label("Port");
        grid.add(portLabel, 0, 1);

        TextField portField = new TextField();
        portField.setPromptText("8020");
        grid.add(portField, 1, 1);

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int port = new Integer(portField.getText());
                if (port > 1024 && port < 10000) {
                    setiNet(iNetField.getText());
                    setPort(new Integer(portField.getText()));
                    iNetStage.close();
                }
            }
        });
        grid.add(submit, 0, 2);

        layout.setCenter(grid);

        iNetStage.setScene(scene);
        iNetStage.showAndWait();
    }
    public static void showError(String errorTitle, String header, String message){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(errorTitle);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }



    public static void main(String[] args) {launch(args);}

    public void setiNet(String iNet) {
        this.iNet = iNet;
    }

    public void setPort(int port) {
        if (port > 1023 && port < 10000)
            this.port = port;
    }
}
