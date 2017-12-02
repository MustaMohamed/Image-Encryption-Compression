package sample.Helpers.CustomExceptions;

public class TreeNotBuiltException extends Exception{
  public TreeNotBuiltException() { super(); }
  
  public TreeNotBuiltException(String message) { super(message); }
  
  public TreeNotBuiltException(String message, Throwable cause) { super(message, cause); }
  
  public TreeNotBuiltException(Throwable cause) { super(cause); }
  
}
