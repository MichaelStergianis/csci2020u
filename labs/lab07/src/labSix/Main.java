package labSix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {

    Canvas canvas;

    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };
    private static String[] warnings = {
            "FLASH FLOOD", "SEVERE THUNDERSTORM",
            "SPECIAL MARINE", "TORNADO"
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Create a scene and group
        Group root = new Group();

        canvas = new Canvas(800, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        draw(gc);
        root.getChildren().add(canvas);

        // Set scene and show
        Scene scene = new Scene(root, 800, 400, Color.LIGHTGRAY);
        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        WarningMap w = new WarningMap();
        w.readWarn();
        // make state variables for drawing
        Integer[] warningValues = w.getWarn();
        double startAng = 0;
        double heightOffset = 100;
        double currLen;
        double sum = 0;
        // set the stroke colour once
        gc.setStroke(Color.BLACK);
        // figure out the total sum
        for (int i = 0; i < 4; i++) {
            sum += warningValues[i];
        }
        for (int i = 0; i < 4; i++) {
            // (warnging / total) * 360 degrees for percentage
            currLen = (warningValues[i] / sum) * 360;
            gc.setFill(pieColours[i]);

            // strokeRect for outline fillRect for fill
            gc.strokeRect(100, heightOffset, 100, 50);
            gc.fillRect(100, heightOffset, 100, 50);

            // add each arc, all centered at same place,
            // each starting at the old angle + old currLen
            gc.fillArc(400, 50, 300, 300, startAng, currLen, ArcType.ROUND);

            // set fill colour to black and offset text 5 pixels away from
            // the end of the rectangle and offset 25 pixels down from the beginning of the
            // rectangle, which is 50 pixels tall
            gc.setFill(Color.BLACK);
            gc.fillText(warnings[i], 205, heightOffset + 25);
            startAng += currLen;

            // increment height offset for rectangles and text
            heightOffset += 50;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
