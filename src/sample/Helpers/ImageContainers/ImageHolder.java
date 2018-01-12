package sample.Helpers.ImageContainers;

import sample.Helpers.CustomExceptions.ImageLoadingException;
import sample.Helpers.CustomExceptions.ImageNotEncryptedException;
import sample.Helpers.ImageControllers.ImageConverter;
import sample.Helpers.ImageControllers.ImageLoader;
import sample.Helpers.ImageControllers.PixelsController;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Instance Class to hold the image data
 *
 * @author Musta Mohamed
 */
public class ImageHolder {
  
  private String mImagePath;
  private File mImageFile;
  private BufferedImage mImageBuffer;
  private Pixel[][] mImageMatrix;
  private Pixel[][] mImageEncryptedMatrix;
  private boolean mIsEncrypted;
  
  /**
   * Object Constructor takes the image file and convert it to a 2D Pixel Matrix
   *
   * @param sourceImageFile File    the image file
   *
   * @throws ImageLoadingException an exception if the image can't be loaded
   */
  public ImageHolder(File sourceImageFile) throws ImageLoadingException {
    this.mImageFile = sourceImageFile;
    this.mImagePath = mImageFile.getAbsolutePath();
    try {
      this.mImageMatrix = ImageLoader.loadImage(mImageFile, false);
    } catch (ImageLoadingException ex) {
      throw new ImageLoadingException("This image can't be loaded...!");
    }
    this.mImageBuffer = ImageConverter.convertPixelsArrayToImage(this.mImageMatrix);
    this.mIsEncrypted = false;
  }
  
  /**
   * Save the image to file
   */
  public void saveImage() {
    ImageLoader.saveImage(this.mImageMatrix);
  }
  
  /**
   * Encrypt the image using key and tap position
   *
   * @param key         int   the key to use it in encryption operation
   * @param tapPosition int   the tap position used in encryption operations
   *
   * @see "PixelsController.Encrypt function documentation"
   */
  public void encryptImage(String key, int tapPosition) {
    this.mIsEncrypted = true;
    this.mImageEncryptedMatrix = PixelsController.Encrypt(key, this.mImageMatrix, tapPosition);
  }
  
  public Pixel[][] getEncryptedMatrix() throws ImageNotEncryptedException {
    if (!mIsEncrypted) {
      throw new ImageNotEncryptedException("This image isn't encrypted..!");
    }
    return this.mImageEncryptedMatrix;
  }
  
  public Pixel[][] getDecryptedMatrix(String key, int tapPosition) throws ImageNotEncryptedException {
    if (!mIsEncrypted) {
      throw new ImageNotEncryptedException("This image isn't decrypted..!");
    }
    return PixelsController.Encrypt(key, this.mImageEncryptedMatrix, tapPosition);
  }
  
  public String getImagePath() {
    return mImagePath;
  }
  
  public void setImagePath(String imagePath) {
    this.mImagePath = imagePath;
  }
  
  public File getImageFile() {
    return mImageFile;
  }
  
  public void setImageFile(File imageFile) {
    this.mImageFile = imageFile;
  }
  
  public BufferedImage getImageBuffer() {
    return mImageBuffer;
  }
  
  public void setImageBuffer(BufferedImage imageBuffer) {
    this.mImageBuffer = imageBuffer;
  }
  
  public Pixel[][] getImageMatrix() {
    return mImageMatrix;
  }
  
  public void setImageMatrix(Pixel[][] imageMatrix) {
    this.mImageMatrix = imageMatrix;
  }
}
