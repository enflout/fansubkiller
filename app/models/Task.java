package models;

import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Ivan Orlov
 */

@Entity
public class Task extends Model {
  @Required
  @MaxSize(100)
  public String text;

  @Required
  @Email
  @MinSize(6)
  @MaxSize(30)
  public String email;

  @Required
  public Short timeout;

  public String toString() {
    return "Task(text: " + text + "; e-mail: " + email + "; timeout: " + timeout + ");";
  }
}
