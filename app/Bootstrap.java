import models.Task;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * @author i.orlov
 */

@OnApplicationStart
public class Bootstrap extends Job {
  public void doJob() {
  }
}
