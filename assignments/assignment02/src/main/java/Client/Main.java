package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.net.Socket;
import java.util.List;

/**
 * Created by michael on 21/03/16.
 */
public class Main extends Application{
    private BorderPane layout;
    public void start(Stage primaryStage) throws Exception {
        Parameters parameters = getParameters();
        List<String> list = parameters.getRaw();
        // Create our layout and scene
        UI ui = new UI();
        ui.start(primaryStage);
//        ConnectionHandler ch = new ConnectionHandler(new Socket("127.0.0.1", 8020));
//        ch.send("/home/michael/tmp/test.py");
//        System.exit(0);
    }
    public static void main(String[] args){
        launch(args);
    }
}
