package models;

import notifiers.Notifier;
import notifiers.SuccessMailer;
import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import ru.fansubkiller.content.SearchResult;
import ru.fansubkiller.search.ToshokanTracker;
import ru.fansubkiller.search.Tracker;
import ru.fansubkiller.timer.CheckerRegistry;

import javax.persistence.Entity;
import java.util.List;

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

  public Task(String text, String email, Short timeout) {
    this.text = text;
    this.email = email;
    this.timeout = timeout;
  }

  @Override
  public boolean create() {
    boolean result = super.create();
    CheckerRegistry.addChecker(this);
    return result;
  }

  @Override
  public Task save() {
    Task updatedTask = super.save();
    CheckerRegistry.reloadChecker(updatedTask);
    return updatedTask;
  }

  @Override
  public Task delete() {
    Task deletedTask = super.delete();
    CheckerRegistry.removeChecker(deletedTask).stop();
    return deletedTask;
  }

  public void execute() {
    if (isExistsResults()) {
      Notifier notifier = new SuccessMailer();
      notifier.taskUpdated(this);
    }
  }

  public boolean isExistsResults() {
    Tracker tracker = new ToshokanTracker();
    List<SearchResult> results = tracker.search(text);
    return results.size() > 0;
  }

  public String toString() {
    return "Task(text: " + text + "; e-mail: " + email + "; timeout: " + timeout + ");";
  }
}
