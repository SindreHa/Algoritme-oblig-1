package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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

    Pane canvas;
    private int screenWidth = 800;
    private int screenHeight = 800;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String melding = showInputDialog("Velg");
        BorderPane pane = new BorderPane();
        canvas = new Pane();
        pane.setCenter(canvas);
        branch(400, 500, -Math.PI / 2, 8);
        primaryStage.setTitle("Algo oblig 1");
        primaryStage.setScene(new Scene(pane, screenWidth, screenHeight));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void branch(int startX, int startY, double vinkel, int antallRekursjoner) {
        if (antallRekursjoner == 0) {
            return;
        }
        int endX = startX + (int) (Math.cos(vinkel) * antallRekursjoner * 10);
        int endY = startY + (int) (Math.sin(vinkel) * antallRekursjoner * 10);

        canvas.getChildren().add(
                new Line(startX, startY, endX, endY)
        );

        branch(endX, endY, vinkel - Math.PI / 8, antallRekursjoner - 1);
        branch(endX, endY, vinkel + Math.PI / 8, antallRekursjoner - 1);
    }
}
