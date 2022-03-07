package co.producter.task.exception;

public class TeamSizeMaxedOutException extends RuntimeException {

  public TeamSizeMaxedOutException(String message) {
    super(message);
  }
}
