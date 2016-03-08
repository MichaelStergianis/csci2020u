package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    // bar chart data
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    // Pie chart data
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Group sub1 = new Group();
        Group sub2 = new Group();
        primaryStage.setTitle("Lab 05 Charts");

//        canvas = new Canvas(800, 600);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        root.getChildren().add(canvas);

        BarChart<String, Number> bc = drawBar();
        PieChart pc = drawPie();
        Scene scene = new Scene(root, 1200, 400, Color.LIGHTGRAY);
        sub1.getChildren().add(bc);
        sub2.getChildren().add(pc);
        root.getChildren().add(sub1);
        root.getChildren().add(sub2);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private BarChart<String, Number> drawBar(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Building Prices Per Year");
        xAxis.setLabel("Year");
        yAxis.setLabel("Value ($)");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Residential");
        for (int i = 0; i < avgHousingPricesByYear.length; i++){
            series1.getData().add(
                    new XYChart.Data(Integer.toString(2008+i), avgHousingPricesByYear[i]));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Commercial");
        for (int i = 0; i < avgCommercialPricesByYear.length; i++){
            series2.getData().add(
                    new XYChart.Data(Integer.toString(2008+i), avgCommercialPricesByYear[i]));
        }
        bc.getData().addAll(series1, series2);

        return bc;
    }

    private PieChart drawPie(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < purchasesByAgeGroup.length; i++){
            pieChartData.add(i, new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]));
        }

        final PieChart pc = new PieChart(pieChartData);

        return pc;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
