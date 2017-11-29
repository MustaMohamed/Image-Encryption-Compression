package sample.Helpers.ImageContainers;

/**
 * Instance class to represent RGB pixels
 *
 * @author Musta Mohamed
 */
public class Pixel {
  
  private int mRed;
  private int mGreen;
  private int mBlue;
  
  /**
   * Object Constructor
   *
   * @param colorValue int the RGB values represented in 24 bits of integer bits
   */
  public Pixel(int colorValue) {
    this.setRed((colorValue >> 16) & 0xff);
    this.setGreen((colorValue >> 8) & 0xff);
    this.setBlue(colorValue & 0xff);
  }
  
  /**
   * Object Overloaded Constructor
   *
   * @param red   int   the red value in color
   * @param green int   the green value in color
   * @param blue  int   the blue value in color
   */
  public Pixel(int red, int green, int blue) {
    this.mRed = red;
    this.mGreen = green;
    this.mBlue = blue;
  }
  
  /**
   * Object Overloaded Constructor
   *
   * @param pixel Pixel   other pixel object
   */
  public Pixel(Pixel pixel) {
    this(pixel.getRGB());
  }
  
  public int getRGB() {
    return (mRed << 16 | mGreen << 8 | mBlue);
  }
  
  public int getRed() {
    return mRed;
  }
  
  public void setRed(int red) {
    this.mRed = red;
  }
  
  public int getGreen() {
    return mGreen;
  }
  
  public void setGreen(int green) {
    this.mGreen = green;
  }
  
  public int getBlue() {
    return mBlue;
  }
  
  public void setBlue(int blue) {
    this.mBlue = blue;
  }
}
