package sample.Helpers.ImageContainers;

import sample.Helpers.CustomExceptions.ImageNotEncryptedException;
import sample.Helpers.CustomExceptions.ImageLoadingException;
import sample.Helpers.ImageControllers.ImageLoader;
import sample.Helpers.ImageControllers.ImageConverter;
import sample.Helpers.ImageControllers.PixelsController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Instance Class to hold the image data
 */
public class ImageHolder {
  
  private String mImagePath;
  private File mImageFile;
  private BufferedImage mImageBuffer;
  private Pixel[][] mImageMatrix;
  private Pixel[][] mImageEncryptedMatrix;
  private boolean mIsEncrypted;
  
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
  
  public void saveImage() {
    ImageLoader.saveImage(this.mImageMatrix);
  }
  
  public void encryptImage(int key, int tapPosition) {
    this.mIsEncrypted = true;
    this.mImageEncryptedMatrix = PixelsController.Encrypt(key, this.mImageMatrix, tapPosition);
  }
  
  public Pixel[][] getEncryptedMatrix() throws ImageNotEncryptedException {
    if(!mIsEncrypted) {
      throw new ImageNotEncryptedException("This image isn't encrypted..!");
    }
    return this.mImageEncryptedMatrix;
  }
  
  public Pixel[][] getDecryptedMatrix(int key, int tapPosition) throws ImageNotEncryptedException {
    if(!mIsEncrypted) {
      throw new ImageNotEncryptedException("This image isn't encrypted..!");
    }
    return PixelsController.Encrypt(key, this.mImageMatrix, tapPosition);
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
