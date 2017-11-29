package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("Views/sample.fxml"));
      primaryStage.setTitle("Example Test");
      primaryStage.setScene(new Scene(root, 1000, 600));
      primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
