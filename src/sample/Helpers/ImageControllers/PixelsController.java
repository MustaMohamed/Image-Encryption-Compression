package sample.Helpers.ImageControllers;


import sample.Helpers.ImageContainers.Pixel;

/**
 * Static Class to deal with Encrypt and Decrypt operations
 *
 * @author Musta Mohamed
 */
public class PixelsController {
  
  private static StringBuilder builderKey;
  private static Pixel[][] targetMatrix;
  private static int matrixHeight;
  private static int matrixWidth;
  private static long keyShifter;
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
  public static Pixel[][] Encrypt(String Key, Pixel[][] sourceMatrix, int tap) {
    
    matrixWidth = ImageLoader.getImageWidth(sourceMatrix);
    matrixHeight = ImageLoader.getImageHeight(sourceMatrix);
    builderKey = new StringBuilder(Key);
    targetMatrix = new Pixel[matrixHeight][matrixWidth];
    if(Key.length() > 63)
      lazyEncrytion(sourceMatrix, tap);
    else {
      keyShifter = convertToInt(Key, Key.length());
      fastEncryption(Key.length() - 1, sourceMatrix, tap);
    }
    return targetMatrix;
  }
  
  
  private static void fastEncryption(int lastPosition, Pixel[][] sourceMatrix, int tap) {
    long shiftedKey = 0;
    for (int i = 0; i < matrixHeight; i++) {
      for (int j = 0; j < matrixWidth; j++) {
      
        int redValue = sourceMatrix[i][j].getRed();
        int greenValue = sourceMatrix[i][j].getGreen();
        int blueValue = sourceMatrix[i][j].getBlue();
      
        // get new shifted key
        shiftedKey = fastShiftKey(lastPosition, tap);
        // XOR the new value of key with the color values
        redValue ^= shiftedKey;
      
        shiftedKey = fastShiftKey(lastPosition, tap);
        greenValue ^= shiftedKey;
      
        shiftedKey = fastShiftKey(lastPosition, tap);
        blueValue ^= shiftedKey;
      
        // set the new pixel colors
        targetMatrix[i][j] = new Pixel(redValue, greenValue, blueValue);
      }
    }
  }
  
  private static int fastShiftKey(int lastPosition, int tap) {
    for(int i = 0;i < 8; i++) {
      int MSBit = (int) ((keyShifter >> lastPosition) & 1);
      int tapBit = (int) ((keyShifter >> tap) & 1);
      keyShifter <<= 1;
      keyShifter |= (MSBit ^ tapBit);
    }
    return (int)(keyShifter & 0xff);
  }
  
  private static void lazyEncrytion(Pixel[][] sourceMatrix, int tap) {
    int shiftedKey = 0;
    for (int i = 0; i < matrixHeight; i++) {
      for (int j = 0; j < matrixWidth; j++) {
      
        int redValue = sourceMatrix[i][j].getRed();
        int greenValue = sourceMatrix[i][j].getGreen();
        int blueValue = sourceMatrix[i][j].getBlue();
      
        // get new shifted key
        shiftedKey = shiftKey(tap);
        // XOR the new value of key with the color values
        blueValue ^= shiftedKey;
      
        shiftedKey = shiftKey(tap);
        greenValue ^= shiftedKey;
      
        shiftedKey = shiftKey(tap);
        redValue ^= shiftedKey;
      
        // set the new pixel colors
        targetMatrix[i][j] = new Pixel(redValue, greenValue, blueValue);
      }
    }
  }
  
  
  /**
   * this function do the shifting operations on the key with tap
   * and return new key value.
   * <p>
   * Linear Feedback Shift Register algorithm is applied
   *
   * @param tap int   the position of the bit to XOR with key
   *
   * @return int      the new key value
   */
  private static int shiftKey(int tap) {
    int lastBit = 0, tapBit = builderKey.length() - tap - 1;
    for(int i = 0; i < 8; i++) {
      if(builderKey.charAt(lastBit) != builderKey.charAt(tapBit)) builderKey.append('1');
      else builderKey.append('0');
      lastBit++; tapBit++;
    }
    String strByte = builderKey.substring(builderKey.length() - 8, builderKey.length());
    builderKey.delete(0, 8);
    return convertToInt(strByte, 8);
  }
  
  
  
  private static int convertToInt(String strByte, int sz) {
    int number = 0;
    for(int i = 0; i < sz; i++) {
      if(strByte.charAt(i) == '1')
        number |= (1 << (sz - i - 1));
    }
    return number;
  }
  
  
}
