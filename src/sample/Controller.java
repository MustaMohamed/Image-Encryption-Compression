package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.Helpers.Compression.CompressionController;
import sample.Helpers.CustomExceptions.ImageLoadingException;
import sample.Helpers.CustomExceptions.ImageNotEncryptedException;
import sample.Helpers.ImageContainers.ImageHolder;
import sample.Helpers.ImageContainers.Pixel;
import sample.Helpers.ImageControllers.ImageConverter;
import sample.Helpers.ImageControllers.ImageLoader;
import sample.Helpers.ImageControllers.PixelsController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
  
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
  
  public static String computeTime(long nanoSecs) {
  
    /*long startTime = System.nanoTime();
    long endTime = System.nanoTime();
    System.out.println(Controller.computeTime(endTime - startTime));*/
    int minutes = (int) (nanoSecs / 60000000000.0);
    int seconds = (int) (nanoSecs / 1000000000.0) - (minutes * 60);
    int millisecs = (int) (((nanoSecs / 1000000000.0) - (seconds + minutes * 60)) * 1000);
    
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
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    
    // Chooser Button get the image file and display it
    btnChooser.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        
        File imageFile = ImageLoader.getImageFile();
        try {
          long startTime = System.nanoTime();
          ImageHolder imageHolder = new ImageHolder(imageFile);
          //ImageLoader.displayImage(imageHolder.getImageBuffer(), firstImageView);
          CompressionController emb = new CompressionController(imageHolder.getImageMatrix());
          
          long endTime = System.nanoTime();
          System.out.println(computeTime(endTime - startTime));
        } catch (ImageLoadingException e) {
          e.printStackTrace();
        }
      }
      
    });

    
  }
  
}
