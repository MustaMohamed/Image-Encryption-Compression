package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.Helpers.CustomExceptions.ImageLoadingException;
import sample.Helpers.CustomExceptions.ImageNotEncryptedException;
import sample.Helpers.ImageContainers.ImageHolder;
import sample.Helpers.ImageControllers.ImageLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
  
  @FXML
  private Pane mainAPPStage;
  
  @FXML
  private ImageView firstImageView;
  
  @FXML
  private ImageView secondImageView;
  
  @FXML
  private Button btnChooser;
  
  @FXML
  private Button btnEncryptImage;
  
  @FXML
  private Button btnDecryptImage;
  
  @FXML
  private TextField txtTapPosition;
  
  @FXML
  private TextField txtKeyValue;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    // Chooser Button get the image file and display it
    btnChooser.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
  
        // first get the image path to a file
        File sourceImageFile = ImageLoader.getImageFile();
        ImageHolder imageHolder = null;
        try {
          imageHolder = new ImageHolder(sourceImageFile);
        } catch (ImageLoadingException e) {
          e.printStackTrace();
        }
        try {
          imageHolder.getEncryptedMatrix();
        } catch (ImageNotEncryptedException ex) {
          System.out.println(ex.getMessage());
        }
        // display the initial image file in first image view
        displayImage(sourceImageFile.getAbsolutePath(), firstImageView);
      }
    });
    
    btnEncryptImage.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
      
      }
    });
    
  }
  
  
  
  private void displayImage(String imageFilePath, ImageView generalImageView) {
    // put the image file in file input stream
    FileInputStream imageThroughFile = null;
    try {
      imageThroughFile = new FileInputStream(imageFilePath);
    } catch (IOException e) {
      System.out.println("Error in display image...! :: " + e.getMessage());
      return;
    }
    Image firstImage = new Image(imageThroughFile);
    generalImageView.setImage(firstImage);
  }
  
  
  
  
  private static String computeTime(long nanoSecs) {
  
    /*long startTime = System.nanoTime();
    long endTime = System.nanoTime();
    System.out.println(Controller.toString(endTime - startTime));*/
    int minutes    = (int) (nanoSecs / 60000000000.0);
    int seconds    = (int) (nanoSecs / 1000000000.0)  - (minutes * 60);
    int millisecs  = (int) ( ((nanoSecs / 1000000000.0) - (seconds + minutes * 60)) * 1000);
    
    if (minutes == 0 && seconds == 0)
      return millisecs + "ms";
    else if (minutes == 0 && millisecs == 0)
      return seconds + "s";
    else if (seconds == 0 && millisecs == 0)
      return minutes + "min";
    else if (minutes == 0)
      return seconds + "s " + millisecs + "ms";
    else if (seconds == 0)
      return minutes + "min " + millisecs + "ms";
    else if (millisecs == 0)
      return minutes + "min " + seconds + "s";
    
    return minutes + "min " + seconds + "s " + millisecs + "ms";
  }
  
}
