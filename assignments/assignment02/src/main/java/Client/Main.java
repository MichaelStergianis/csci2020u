package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.Socket;

/**
 * Created by michael on 21/03/16.
 */
public class Main extends Application{
    private BorderPane layout;
    public void start(Stage primaryStage) throws Exception {
        // Create our layout and scene
        layout = new BorderPane();
        Scene scene = new Scene(layout, 800, 600);

        Socket serverSocket = new Socket("127.0.0.1", 8080);
        ConnectionHandler handler = new ConnectionHandler(serverSocket);
        String[] dirs = handler.receiveDirs();
        System.out.println(dirs[0]);

        // Construct the primary window based on our scene
        primaryStage.setTitle("File Transfer Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
