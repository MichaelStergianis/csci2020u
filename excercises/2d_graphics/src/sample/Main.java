package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Graphics Demo");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw();
        drawFrame();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.color(0.0, 0.0, 1.0, .75));
        gc.strokeLine(50,50,150,250);

        // rectangular stuff
        gc.setStroke(Color.ORANGE);
        gc.setFill(Color.OLIVE);
        gc.fillRect(250, 50, 100, 75);
        gc.strokeRect(250, 175, 100, 75);
        gc.fillRoundRect(450, 50, 100, 75, 10, 10);
        gc.strokeRoundRect(450, 175, 100, 75, 20, 20);

        // round stuff
        gc.setStroke(Color.MAROON);
        gc.strokeOval(650, 50, 100, 75);
        gc.fillOval(650, 175, 100, 75);
        gc.strokeArc(50, 350, 100, 75, 115, 45, ArcType.ROUND);
        gc.fillArc(50, 500, 100, 75, 45, 115, ArcType.CHORD);

        // polygonal stuff
        gc.setFill(Color.KHAKI);
        gc.setStroke(Color.CORAL);
        gc.strokePolygon(new double[] {250.0, 310.0, 300.0, 250.0},
                         new double[] {350.0, 360.0, 380.0, 400.0}, 4);

        Font font = new Font("Verdana", 24);
        gc.setFont(font);
        gc.setFill(Color.CHOCOLATE);
        gc.setStroke(Color.ALICEBLUE);
        // gc.strokeText();

        // static image
        // Image disk = new Image("disk.png");
        // gc.drawImage(disk, 685, 400);
    }

    private int frameOffsetX = 0;
    private int frameOffsetY = 0;

    private void drawFrame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image spriteSheet = new Image("sprites.png");

        int frameWidth = 128;
        int frameHeight = 128;
        int totalWidth = 768;
        int totalHeight = 1536;
        int numCols = 6;


        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);



        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        gc.setFill(Color.LIGHTGRAY);
                        gc.fillRect(685, 500, frameWidth, frameHeight);

                        gc.drawImage(spriteSheet,
                                frameOffsetX, frameOffsetY,
                                frameWidth, frameHeight,
                                685, 500, frameWidth, frameHeight);

                        frameOffsetX += frameWidth;
                        if (frameOffsetX >= totalWidth) {
                            frameOffsetX = 0;
                            frameOffsetY += frameHeight;
                            if (frameOffsetY >= totalHeight){
                                frameOffsetY = 0;
                            }
                        }
                    }
                }));
        timeline.playFromStart();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
