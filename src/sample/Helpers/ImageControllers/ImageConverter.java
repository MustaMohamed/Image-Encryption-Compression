package sample.Helpers.ImageControllers;

import org.jetbrains.annotations.NotNull;
import sample.Helpers.ImageContainers.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import static sample.Helpers.ImageControllers.ImageLoader.getImageHeight;
import static sample.Helpers.ImageControllers.ImageLoader.getImageWidth;

/**
 * Static class has static functions deal with converting image
 *
 * @author Musta Mohamed
 */

public class ImageConverter {
  
  /**
   * this function convert 2D Pixels Matrix to Image buffer that we can save
   *
   * @param sourcePixelsArray 2D Pixel  the source image in Matrix of Color pixels
   *
   * @return BufferedImage              the image buffer that can be saved
   */
  public static BufferedImage convertPixelsArrayToImage(@NotNull Pixel[][] sourcePixelsArray) {
    // get height and width of the image
    int imageHeight = getImageHeight(sourcePixelsArray),
      imageWidth = getImageWidth(sourcePixelsArray);
    // create Image file of same size of the 2D array
    BufferedImage targetImage = new BufferedImage(imageWidth, imageHeight,
      BufferedImage.TYPE_INT_RGB);
    
    for (int i = 0; i < imageHeight - 1; i++) {
      
      for (int j = 0; j < imageWidth - 1; j++) {
        
        // convert the pixels to integer value
        targetImage.setRGB(j, i, sourcePixelsArray[i][j].getRGB());
      }
    }
    return targetImage;
  }
  
  /**
   * This function takes BufferImage class var and make operations on the pixels
   * <p>
   * Convert each RGB value to a color pixel by faster method
   *
   * @param sourceImageBuffer BufferImage   the image buffer has the image
   *
   * @return 2D Pixel                       the Matrix of Color pixels
   */
  public static Pixel[][] fastConvertImageToPixelsArray(BufferedImage sourceImageBuffer) {
    // get height and width of the image
    int imageHeight = sourceImageBuffer.getHeight(),
      imageWidth = sourceImageBuffer.getWidth();
    
    // create 2D array of pixels color of same size of the image
    Pixel[][] targetArray = new Pixel[imageHeight][imageWidth];
    
    final byte[] pixels = ((DataBufferByte) sourceImageBuffer.getRaster().getDataBuffer()).getData();
    
    if (sourceImageBuffer.getAlphaRaster() == null) {
      for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += 3) {
        
        int argb = 0;
        argb += -16777216; // 255 alpha
        argb += ((int) pixels[pixel] & 0xff); // blue
        argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
        argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
        
        // convert the integer value to RGB value red - green - blue
        targetArray[row][col] = new Pixel(argb);
        
        col++;
        if (col == imageWidth) {
          col = 0; row++;
        }
      }
    } else {
      for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += 4) {
        
        int argb = 0;
        argb |= (((int) pixels[pixel + 1] & 0xff)); // blue
        argb |= (((int) pixels[pixel + 2] & 0xff) << 8); // green
        argb |= (((int) pixels[pixel + 3] & 0xff) << 16); // red
        
        // convert the integer value to RGB value red - green - blue
        targetArray[row][col] = new Pixel(argb);
        
        col++;
        if (col == imageWidth) {
          col = 0; row++;
        }
      }
    }
    return targetArray;
  }
  
  /**
   * This function takes BufferImage class var and make operations on the pixels.
   * <p>
   * Convert each RGB value to a color pixel
   *
   * @param sourceImageBuffer BufferImage   the image buffer has the image
   *
   * @return 2D Pixel                       the Matrix of Color pixels
   */
  public static Pixel[][] convertImageToPixelsArray(BufferedImage sourceImageBuffer) {
    // get height and width of the image
    int imageHeight = sourceImageBuffer.getHeight(),
      imageWidth = sourceImageBuffer.getWidth();
    
    // create 2D array of pixels color of same size of the image
    Pixel[][] targetArray = new Pixel[imageHeight][imageWidth];
    
    for (int i = 0; i < imageHeight; i++) {
      
      for (int j = 0; j < imageWidth; j++) {
        
        // convert the integer value to RGB value red - green - blue
        Color tempColor = new Color(sourceImageBuffer.getRGB(j, i));
        
        int redValue = tempColor.getRed(), greenValue = tempColor.getGreen(),
          blueValue = tempColor.getBlue();
        
        targetArray[i][j] = new Pixel(redValue, greenValue, blueValue);
      }
    }
    return targetArray;
  }
  
}
