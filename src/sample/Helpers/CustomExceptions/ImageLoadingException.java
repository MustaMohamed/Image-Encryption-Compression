package sample.Helpers.CustomExceptions;

public class ImageLoadingException extends Exception {
  public ImageLoadingException() { super(); }
  
  public ImageLoadingException(String message) { super(message); }
  
  public ImageLoadingException(String message, Throwable cause) { super(message, cause); }
  
  public ImageLoadingException(Throwable cause) { super(cause); }
}
