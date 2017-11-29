package sample.Helpers.ImageContainers;


/**
 * Instance class to represent RGB pixels
 */
public class Pixel {
  private int mRed;
  private int mGreen;
  private int mBlue;
  
  
  public Pixel(int colorValue) {
    this.setRed((colorValue >> 16) & 0xff);
    this.setGreen((colorValue >> 8) & 0xff);
    this.setBlue(colorValue & 0xff);
  }
  
  public Pixel(int red, int green, int blue) {
    this.mRed = red;
    this.mGreen = green;
    this.mBlue = blue;
  }
  
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
