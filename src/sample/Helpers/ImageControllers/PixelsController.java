package sample.Helpers.ImageControllers;


import sample.Helpers.ImageContainers.Pixel;

/**
 * Static Class to deal with Encrypt and Decrypt operations
 *
 * @author Musta Mohamed
 */
public class PixelsController {
  
  /**
   * this function encrypting an image matrix with key and tap position.
   * <p>
   * by using Linear Feedback Shift Register algorithm
   *
   * @param Key          int       the key of the encryption operation
   * @param sourceMatrix 2D Pixel  array hols the source image to encrypted
   * @param tap          int       the position of the bit to XOR with key value
   *
   * @return 2D Pixel              the encrypted Matrix
   */
  public static Pixel[][] Encrypt(int Key, Pixel[][] sourceMatrix, int tap) {
    
    int matrixWidth = ImageLoader.getImageWidth(sourceMatrix);
    int matrixHeight = ImageLoader.getImageHeight(sourceMatrix);
    int shiftedKey = Key;
    
    Pixel[][] targetMatrix = new Pixel[matrixHeight][matrixWidth];
    for (int i = 0; i < matrixHeight; i++) {
      for (int j = 0; j < matrixWidth; j++) {
        
        int redValue = sourceMatrix[i][j].getRed();
        int greenValue = sourceMatrix[i][j].getGreen();
        int blueValue = sourceMatrix[i][j].getBlue();
        
        // get new shifted key
        shiftedKey = shiftKey(shiftedKey, tap);
        // XOR the new value of key with the color values
        blueValue ^= shiftedKey;
        
        shiftedKey = shiftKey(shiftedKey, tap);
        greenValue ^= shiftedKey;
        
        shiftedKey = shiftKey(shiftedKey, tap);
        redValue ^= shiftedKey;
        
        // set the new pixel colors
        targetMatrix[i][j] = new Pixel(redValue, greenValue, blueValue);
      }
    }
    
    
    return targetMatrix;
  }
  
  
  /**
   * this function do the shifting operations on the key with tap
   * and return new key value.
   * <p>
   * Linear Feedback Shift Register algorithm is applied
   *
   * @param Key int   the value to be shifted
   * @param tap int   the position of the bit to XOR with key
   *
   * @return int      the new key value
   */
  private static int shiftKey(int Key, int tap) {
    for (int i = 0; i < 8; i++) {
      // get the bit in tap position
      int tapShiftIdx = ((Key >> (tap - 1)) & 1);
      // get the MS Bit
      int MSBit = ((Key >> 7) & 1);
      // XOR the result
      int XORBit = (tapShiftIdx ^ MSBit);
      // shift the key
      Key <<= 1;
      // set the LS Bit
      Key |= XORBit;
    }
    return Key;
  }
  
  
}
