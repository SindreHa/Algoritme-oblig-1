package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


import static javax.swing.JOptionPane.*;
/**
 *
 @author Jan Andreas Sletta og Sindre Haavaldsen
 *
 */

public class Main extends Application {

    private final int screenWidth = 800;
    private final int screenHeight = 800;

    private Pane canvas;
    private HBox infoWindow;

    private TextField treeLevels;
    private TextField sizeRoot;
    private Slider angle;
    private Slider randomFactorSlider;
    private Slider tykkhet;


    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane pane = new BorderPane();
        canvas = new Pane();
        pane.setCenter(canvas);
        pane.setBottom(setInfoVindu());


        angle.valueProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.getChildren().clear();
            if(treeLevels.getText().equals(""))
                branch(400,500, -Math.PI/2, 8, angle.getValue(), tykkhet.getValue());
            else
                branch(400,500, -Math.PI/2, Integer.parseInt(treeLevels.getText()), angle.getValue(), tykkhet.getValue());
        }));

        tykkhet.valueProperty().addListener(((observable, oldValue, newValue) -> {
            canvas.getChildren().clear();
            if(treeLevels.getText().equals(""))
                branch(400,500, -Math.PI/2, 8, angle.getValue(), tykkhet.getValue());
            else
                branch(400,500, -Math.PI/2, Integer.parseInt(treeLevels.getText()), angle.getValue(), tykkhet.getValue());
        }));

        primaryStage.setTitle("Algo oblig 1");
        primaryStage.setScene(new Scene(pane, screenWidth, screenHeight));
        primaryStage.show();
    }


    public void branch(int startX, int startY, double vinkel, int antallRekursjoner, double greinVinkel, double tykkhet) {
        if (antallRekursjoner == 0) {
            return;
        }
        int endX = startX + (int) (Math.cos(vinkel) * antallRekursjoner * 10);
        int endY = startY + (int) (Math.sin(vinkel) * antallRekursjoner * 10);

        Line line = new Line(startX, startY, endX, endY);
        line.setStrokeWidth(tykkhet);
        canvas.getChildren().add(line);

        branch(endX, endY, vinkel - Math.PI / greinVinkel, antallRekursjoner - 1, greinVinkel, tykkhet-1);
        branch(endX, endY, vinkel + Math.PI / greinVinkel, antallRekursjoner - 1, greinVinkel, tykkhet-1);
    }

    private GridPane setInfoVindu(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(13, 13, 13,13));
        grid.setHgap(15);
        grid.setVgap(5);

        Label treeLevelsLabel = new Label("Nivå med greiner");
        treeLevels = new TextField();

        Label sizeRootLabel = new Label("Størrelse på greiner");
        sizeRoot = new TextField();

        Label angleLabel = new Label("Vinkel");
        angle = new Slider();

        Label randomFactorSliderLabel = new Label("Tilfeldighets faktor");
        randomFactorSlider = new Slider();

        Label strokeWidthLabel = new Label("Tykkhet");
        tykkhet = new Slider();


        grid.add(treeLevelsLabel, 0,0);
        grid.add(treeLevels, 0,1);

        grid.add(sizeRootLabel, 1,0);
        grid.add(sizeRoot, 1,1);

        grid.add(angleLabel, 2,0);
        grid.add(angle, 2,1);

        grid.add(randomFactorSliderLabel, 3, 0);
        grid.add(randomFactorSlider, 3,1);

        grid.add(strokeWidthLabel, 4, 0);
        grid.add(tykkhet, 4,1);

        return grid;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
