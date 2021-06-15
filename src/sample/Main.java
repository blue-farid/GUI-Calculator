package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader ( getClass().getResource( "sample.fxml" ));
            loader.setController(new Controller());
            Parent root = loader.load();
            Scene scene = new Scene(root,273,343);
            primaryStage.setScene(scene);
            primaryStage.setTitle("GUI-Calculator");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
