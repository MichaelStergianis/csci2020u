package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.LIGHTGRAY);

        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());
        root.getChildren().add(canvas);

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Graphics demo");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.color(0.0, 0.0, 1.0, 0.75));
        gc.strokeLine(50,50,150,250);

        // rectangular stuff
        gc.setStroke(Color.ORANGE);
        gc.setFill(Color.OLIVE);
        gc.fillRect(250, 50, 100, 75);
        gc.strokeRect(250, 175, 100, 75);
        gc.fillRoundRect(450, 50, 100, 75, 10, 10);
        gc.strokeRoundRect(450, 175, 100, 75, 20, 20);

        // rounded stuff
        gc.setStroke(Color.MAROON);
        gc.setFill(Color.AZURE);
        gc.strokeOval(650, 50, 100, 75);
        gc.fillOval(650, 175, 100, 75);
        gc.strokeArc(50, 350, 100, 75, 115, 45, ArcType.ROUND);
        gc.fillArc(50, 500, 100, 75, 45, 115, ArcType.CHORD);

        // polygonal stuff
        gc.setFill(Color.KHAKI);
        gc.setStroke(Color.CORAL);
        gc.strokePolygon(new double[] {250, 310, 300, 250},
                         new double[] {350,360,380,400}, 4);
        gc.fillPolygon(new double[] {250, 310, 300, 250},
                       new double[] {500,510,530,550}, 4);

        // text
        Font font = new Font("Verdana", 24);
        gc.setFont(font);
        gc.setFill(Color.CHOCOLATE);
        gc.setStroke(Color.ALICEBLUE);
        gc.strokeText("CSCI 2020u", 450, 400);
        gc.fillText("CSCI 2020u", 450, 550);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
