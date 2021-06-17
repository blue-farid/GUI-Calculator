package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class
 * @author Farid
 * @version 2.2
 *
 * github.com/farid-127
 *
 */

public class Main extends Application {

    /**
     * start of the application.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader ( getClass().getResource( "sample.fxml" ));
            loader.setController(new Controller());
            Parent root = loader.load();
            Scene scene = new Scene(root,273,343);
            primaryStage.setScene(scene);
            primaryStage.setTitle("GUI-Calculator");
            // set resizable option false.
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
