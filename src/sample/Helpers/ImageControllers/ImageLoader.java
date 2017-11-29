package sample.Helpers.ImageControllers;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import sample.Helpers.CustomExceptions.ImageLoadingException;
import sample.Helpers.ImageContainers.Pixel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Static class has static functions deal with images
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
   *
   * @author Musta Mohamed
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
    if (fast)
      return ImageConverter.fastConvertImageToPixelsArray(myImage);
    return ImageConverter.convertImageToPixelsArray(myImage);
  }
  
  /**
   * @return
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
   * @param source 2D Pixel
   *
   * @return int the width of the image array
   */
  public static int getImageWidth(Pixel[][] source) {
    if (source.length <= 0)
      return 0;
    return source[0].length;
  }
  
  /**
   * this function save the image source from 2D array of pixels
   * save as a png image format in desktop or selected path
   *
   * @param sourcePixelsArray the image pixels array
   *
   * @return String the path of the saved image file
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
   * @return File the empty file formatted for the saved image
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
   * @param targetImage BufferedImage the image buffer that we can write in file
   * @param imageFile   File the empty file that we'll save image in it
   */
  private static void writeImageFile(BufferedImage targetImage, File imageFile) {
    
    try {
      // write the image in a file of png format
      ImageIO.write(targetImage, "jpg", imageFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
