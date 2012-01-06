package ru.fansubkiller.test;

import ru.fansubkiller.search.ToshokanTracker;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author i.orlov
 */

public class ToshokanTrackerTest {
  @Test
  public void formSearchURL() {
    String searchText = "Harry Potter 03";
    ToshokanTracker tracker = new ToshokanTracker();
    String searchURL = tracker.formSearchURL(searchText);
    assertEquals("http://tokyotosho.info/search.php?terms=Harry+Potter+03&type=0&size_min=&size_max=&username=", searchURL);
  }
}
