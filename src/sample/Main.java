package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Random;

/**
 *
 @author Jan Andreas Sletta og Sindre Haavaldsen
 *
 * Programmet består av en rekursiv metode som tegner et tre med flere parametere som styrer
 * hvordan det ender opp
 *
 */

public class Main extends Application {

    private final int screenWidth = 800;
    private final int screenHeight = 800;
    private int startY = 700;
    private int rootInsurance = 3;

    private Pane canvas;

    private Slider recursionCount;
    private Slider branchSize;
    private Slider branchAngle;
    private Slider randomFactorSlider;
    private Slider branchWidth;
    private ColorPicker treeColor;


    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane pane = new BorderPane();
        canvas = new Pane();
        pane.setCenter(canvas);
        pane.setBottom(setInfoVindu());
        lagTre(400,startY, -Math.PI/2, (int) recursionCount.getValue(),
                branchAngle.getValue(), branchWidth.getValue(), branchSize.getValue(), (int)randomFactorSlider.getValue(), rootInsurance, treeColor.getValue());


        branchAngle.valueProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.getChildren().clear();
            lagTre(400,startY, -Math.PI/2, (int) recursionCount.getValue(),
                    branchAngle.getValue(), branchWidth.getValue(), branchSize.getValue(), (int)randomFactorSlider.getValue(), rootInsurance, treeColor.getValue());
        }));

        branchWidth.valueProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.getChildren().clear();
            lagTre(400,startY, -Math.PI/2, (int) recursionCount.getValue(),
                    branchAngle.getValue(), branchWidth.getValue(), branchSize.getValue(), (int)randomFactorSlider.getValue(), rootInsurance, treeColor.getValue());
        }));

        recursionCount.valueProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.getChildren().clear();
            lagTre(400,startY, -Math.PI/2, (int) recursionCount.getValue(),
                    branchAngle.getValue(), branchWidth.getValue(), branchSize.getValue(), (int)randomFactorSlider.getValue(), rootInsurance, treeColor.getValue());
        }));

        branchSize.valueProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.getChildren().clear();
            lagTre(400,startY, -Math.PI/2, (int) recursionCount.getValue(),
                    branchAngle.getValue(), branchWidth.getValue(), branchSize.getValue(), (int)randomFactorSlider.getValue(), rootInsurance, treeColor.getValue());
        }));

        randomFactorSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.getChildren().clear();
            lagTre(400,startY, -Math.PI/2, (int) recursionCount.getValue(),
                    branchAngle.getValue(), branchWidth.getValue(), branchSize.getValue(), (int)randomFactorSlider.getValue(), rootInsurance, treeColor.getValue());
        }));

        treeColor.setOnAction((EventHandler) t -> {
            canvas.getChildren().clear();
            lagTre(400,startY, -Math.PI/2, (int) recursionCount.getValue(),
                    branchAngle.getValue(), branchWidth.getValue(), branchSize.getValue(), (int)randomFactorSlider.getValue(), rootInsurance, treeColor.getValue());
        });

        primaryStage.setTitle("Algo oblig 1");
        primaryStage.setScene(new Scene(pane, screenWidth, screenHeight));
        primaryStage.show();
    }


    public void lagTre(int startX, int startY, double growingAngle, int recursionCount, double branchAngle,
                       double branchWidth, double branchSize, int randomFactor, int rootInsurance, Color treeColor) {
        if (recursionCount == 0) {
            return;
        }
        Random random = new Random();
        double randomValue = .85 + (1 - .85) * random.nextDouble();
        int endX = startX + (int) (Math.cos(growingAngle) * (branchSize * randomValue) * 10);
        int endY = startY + (int) (Math.sin(growingAngle) * (branchSize* randomValue) * 10);

        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(branchWidth);
        line.setStroke(treeColor);
        canvas.getChildren().add(line);



        if(rootInsurance == 0) {
            if (random.nextInt(100) > randomFactor)
                lagTre(endX, endY, growingAngle - Math.PI / (branchAngle * randomValue), recursionCount - 1,
                        branchAngle, branchWidth - 1, branchSize - 1, randomFactor, 0, treeColor);

            if (random.nextInt(100) > randomFactor)
                lagTre(endX, endY, growingAngle + Math.PI / (branchAngle * randomValue), recursionCount - 1,
                        branchAngle, branchWidth - 1, branchSize - 1, randomFactor, 0, treeColor);
        }else{
            lagTre(endX, endY, growingAngle - Math.PI / (branchAngle * randomValue), recursionCount - 1,
                    branchAngle, branchWidth - 1, branchSize - 1, randomFactor, rootInsurance-1, treeColor);
            lagTre(endX, endY, growingAngle + Math.PI / (branchAngle * randomValue), recursionCount - 1,
                    branchAngle, branchWidth - 1, branchSize - 1, randomFactor, rootInsurance-1, treeColor);
        }
    }

    private GridPane setInfoVindu(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(13, 13, 13,13));
        grid.setHgap(15);
        grid.setVgap(5);

        Label treeLevelsLabel = new Label("Nivå med greiner");
        recursionCount = new Slider(0,10,.5);
        recursionCount.setValue(8);

        Label sizeRootLabel = new Label("Størrelse på greiner");
        branchSize = new Slider(5, 30, .5);
        branchSize.setValue(10);

        Label angleLabel = new Label("Vinkel");
        branchAngle = new Slider(0, 20, .5);
        branchAngle.setValue(7);

        Label randomFactorSliderLabel = new Label("Tilfeldighets faktor");
        randomFactorSlider = new Slider(0,100, .5);
        randomFactorSlider.setValue(70);

        Label strokeWidthLabel = new Label("Tykkhet");
        branchWidth = new Slider(0,20, .5);
        branchWidth.setValue(10);

        treeColor = new ColorPicker();
        treeColor.setValue(Color.BLACK);

        grid.add(treeLevelsLabel, 0,0);
        grid.add(recursionCount, 0,1);

        grid.add(sizeRootLabel, 1,0);
        grid.add(branchSize, 1,1);

        grid.add(angleLabel, 2,0);
        grid.add(branchAngle, 2,1);

        grid.add(randomFactorSliderLabel, 3, 0);
        grid.add(randomFactorSlider, 3,1);

        grid.add(strokeWidthLabel, 4, 0);
        grid.add(branchWidth, 4,1);

        grid.add(treeColor, 5, 1);

        return grid;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
