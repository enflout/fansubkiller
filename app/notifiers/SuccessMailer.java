package notifiers;

import play.mvc.Mailer;

/**
 * @author i.orlov
 */
public class SuccessMailer extends Mailer {
  public static void success() {
    setSubject("Welcome");
    addRecipient("enflout@gmail.com");
    setFrom("Me <me@me.com>");
    send();
   }
}
