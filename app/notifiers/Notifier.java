package notifiers;

import models.Task;

/**
 * @author Ivan Orlov
 */

public interface Notifier {
  public void taskUpdated(Task task);
}
