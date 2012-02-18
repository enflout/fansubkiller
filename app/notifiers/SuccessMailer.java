package notifiers;

import models.Task;
import play.mvc.Mailer;

/**
 * @author i.orlov
 */
public class SuccessMailer extends Mailer implements Notifier {

  public static void success(Task task) {
    setSubject("\"" + task.text + "\" is updated!");
    addRecipient(task.email);
    setFrom("Fansubkiller FSK <fansubkiller@gmail.com>");
    send();
  }

  public void taskUpdated(Task task) {
    success(task);
  }
}
