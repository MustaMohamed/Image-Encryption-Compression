package sample.Helpers.ImageControllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import sample.Helpers.CustomExceptions.ImageLoadingException;
import sample.Helpers.ImageContainers.Pixel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Static class has static functions deal with images
 *
 * @author Musta Mohamed
 */
public class ImageLoader {
  
  // the path of the selected image file
  private static String sourceImagePath;
  // the default path of the saved image file
  private static String targetImagePath = "/home/musta/Desktop/temp";
  // the counter of the file name if the file exist create new one
  private static int targetImagesCounter = 0;
  
  /**
   * static function takes an Image as a File and try to convert it to 2D array of Pixels
   *
   * @param sourceImageFile File    the Image File
   *
   * @return 2D Pixel               array of Color pixels from the selected method
   */
  public static Pixel[][] loadImage(@NotNull File sourceImageFile, boolean fast)
    throws ImageLoadingException {
    
    //save the Image path
    sourceImagePath = sourceImageFile.getAbsolutePath();
    
    // the Image Buffer
    BufferedImage myImage;
    try {
      myImage = ImageIO.read(sourceImageFile);
    } catch (IOException e) {
      throw new ImageLoadingException("File Exception, Can't load image File...!");
    }
    if (fast) // Choose the method to be used
      return ImageConverter.fastConvertImageToPixelsArray(myImage);
    return ImageConverter.convertImageToPixelsArray(myImage);
  }
  
  /**
   * By using FileChooser select the image file to deal with
   *
   * @return File   the file that contains the selected image
   */
  public static File getImageFile() {
    
    // get the image file from file chooser
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select an image");
    File imageFile = null;
    
    try {
      imageFile = fileChooser.showOpenDialog(new Stage());
    } catch (Exception e) {
      System.out.println("Error in loading image file...! :: " + e.getMessage());
    }
    return imageFile;
  }
  
  /**
   * Displaying the image in an ImageView UI component
   *
   * @param imageFilePath    String      the image path to be displayed
   * @param generalImageView ImageView   the UI component to display the image on it
   */
  public static void displayImage(String imageFilePath, ImageView generalImageView) {
    // put the image file in file input stream
    FileInputStream imageThroughFile = null;
    try {
      imageThroughFile = new FileInputStream(imageFilePath);
    } catch (IOException e) {
      System.out.println("Error in display image...! :: " + e.getMessage());
      return;
    }
    Image outImage = new Image(imageThroughFile);
    generalImageView.setImage(outImage);
  }
  
  /**
   * OverLoaded function of displaying the image in an ImageView UI component.
   *
   * @param imageBufferedFormat BufferedImage   the image Buffered format to be displayed
   * @param generalImageView    ImageView       the UI component to display the image on it
   */
  public static void displayImage(BufferedImage imageBufferedFormat, ImageView generalImageView) {
    
    // Convert the BufferedImage to Image
    Image outImage = SwingFXUtils.toFXImage(imageBufferedFormat, null);
    
    generalImageView.setImage(outImage);
  }
  
  
  /**
   * compute the height of the array
   *
   * @param source 2D Pixel   the source Matrix
   *
   * @return int              the height of the image array
   */
  public static int getImageHeight(@NotNull Pixel[][] source) {
    return source.length;
  }
  
  /**
   * compute the width of the array
   *
   * @param source 2D Pixel   the source Matrix
   *
   * @return int              the width of the image array
   */
  public static int getImageWidth(Pixel[][] source) {
    if (source.length <= 0)
      return 0;
    return source[0].length;
  }
  
  /**
   * this function save the image source from 2D array of pixels.
   * <p>
   * Save the image as a png image format in desktop or selected path
   *
   * @param sourcePixelsArray the image pixels array
   *
   * @return String           the path of the saved image file
   */
  public static String saveImage(@NotNull Pixel[][] sourcePixelsArray) {
    // get height and width of the image
    int imageHeight = getImageHeight(sourcePixelsArray),
      imageWidth = getImageWidth(sourcePixelsArray);
    
    // create Image file of same size of the 2D array
    BufferedImage targetImage = ImageConverter.convertPixelsArrayToImage(sourcePixelsArray);
    
    // save the image file
    File imageFile = saveFileImage();
    
    // write the image to the file
    writeImageFile(targetImage, imageFile);
    
    return imageFile.getAbsoluteFile().toString();
  }
  
  
  /**
   * this function create an image file to save the buffered image in it
   *
   * @return File   the empty file formatted for the saved image
   */
  private static File saveFileImage() {
    
    File imageFile = new File(targetImagePath);
    
    //make new file name
    while (imageFile.exists()) {
      targetImagesCounter++;
      String tempTargetImagePath = targetImagePath + targetImagesCounter;
      imageFile = new File(tempTargetImagePath);
    }
    
    return imageFile;
  }
  
  /**
   * this function write the image data to a file
   *
   * @param targetImage BufferedImage   the image buffer that we can write in file
   * @param imageFile   File            the empty file that we'll save image in it
   */
  private static void writeImageFile(BufferedImage targetImage, File imageFile) {
    
    try {
      // write the image in a file of png format
      ImageIO.write(targetImage, "bmp", imageFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
