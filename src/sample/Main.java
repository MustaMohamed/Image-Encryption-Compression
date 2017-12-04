package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Helpers.HuffmanTree.HuffmanNode;
import sample.Helpers.HuffmanTree.HuffmanTree;

import java.io.FileOutputStream;
import java.io.IOException;


public class Main extends Application {
  
  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Views/sample.fxml"));
    primaryStage.setTitle("Example Test");
    primaryStage.setScene(new Scene(root, 1000, 600));
    primaryStage.show();
    
    System.out.println("Done..!!");
  }
  
}
