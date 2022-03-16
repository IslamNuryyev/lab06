package lab06;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {
    private static double[] avgHousingPricesByYear = {  
        247381.0,264171.4,287715.3,294736.1,  
        308431.4,322635.9,340253.0,363153.7  
    };  
    
    private static double[] avgCommercialPricesByYear = {  
        1121585.3,1219479.5,1246354.2,1295364.8,         
        1335932.6,1472362.0,1583521.9,1613246.3  
    };

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



    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setTitle("Lab06");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

        Canvas canvas = null;
        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawBarChart(gc);
        drawPieChart(gc);
    }

    private void drawBarChart(GraphicsContext gc) {
        int yearWidth = 50;
        int barWidth = 20;
        int bottom = 550;
        int left = 50;
        double maxPrice = 1613246.3;
        int currentX = left + 5;

        for (int i = 0; i < avgHousingPricesByYear.length; i++) {
            int barHeight = (int)(avgHousingPricesByYear[i] / maxPrice * 500.0);
            gc.setFill(Color.RED);
            gc.fillRect(currentX, bottom - barHeight, barWidth, barHeight);

            barHeight = (int)(avgCommercialPricesByYear[i] / maxPrice * 500.0);
            gc.setFill(Color.BLUE);
            gc.fillRect(currentX + barWidth, bottom - barHeight, barWidth, barHeight);

            currentX += yearWidth;
        }
    }  

    private void drawPieChart(GraphicsContext gc) {
        int total = 0;

        for (int i = 0; i < purchasesByAgeGroup.length; i++) {
            total += purchasesByAgeGroup[i];
        }

        double startAngle = 0.0;

        for (int i = 0; i < purchasesByAgeGroup.length; i++) {
            double percent = (double)purchasesByAgeGroup[i] / (double)total;
            double sweepAngle = percent * 360.0;

            gc.setFill(pieColours[i]);
            gc.fillArc(550, 100, 400, 400, startAngle, sweepAngle, ArcType.ROUND);

            startAngle += sweepAngle;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}