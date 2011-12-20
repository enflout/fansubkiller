import play.jobs.Job;
import play.jobs.OnApplicationStart;

/**
 * @author i.orlov
 */

@OnApplicationStart
public class Bootstrap extends Job {
  public void doJob() {
    // if db is empty
    //if (User.count() == 0) {
    //  Fixtures.load("initial-data.yml");
    //}
  }
}
