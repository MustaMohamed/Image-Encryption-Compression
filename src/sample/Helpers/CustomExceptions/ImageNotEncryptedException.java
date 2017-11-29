package sample.Helpers.CustomExceptions;

public class ImageNotEncryptedException extends Exception {
  public ImageNotEncryptedException() { super(); }
  
  public ImageNotEncryptedException(String message) { super(message); }
  
  public ImageNotEncryptedException(String message, Throwable cause) { super(message, cause); }
  
  public ImageNotEncryptedException(Throwable cause) { super(cause); }
}
