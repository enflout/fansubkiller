package ru.fansubkiller.test;

import ru.fansubkiller.search.ToshokanTracker;
import ru.fansubkiller.search.Tracker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author i.orlov
 */
public class TrackerTest {
  @Test
  public void testReplaceSpacesByPluses() {
    String query = "слово слово слово слово   слово  слово словослово 64hgfh fghtr h rh 6h nbfghbf";
    String answer = Tracker.replaceSpacesByPluses(query);
    assertEquals("слово+слово+слово+слово+++слово++слово+словослово+64hgfh+fghtr+h+rh+6h+nbfghbf", answer);
  }

  @Test(expected=NullPointerException.class)
  public void replaceSpacesByPlusesWithNullString() {
    String query = null;
    // must throw NPE
    String answer = Tracker.replaceSpacesByPluses(query);
  }

  @Test
  public void replaceSpacesByPlusesWithZeroLengthString() {
    String query = "";
    String answer = Tracker.replaceSpacesByPluses(query);
    assertEquals("", answer);
  }

  @Test
  public void replaceSpacesByPlusesWithNoSpacesString() {
    String query = "no_spaces_string";
    String answer = Tracker.replaceSpacesByPluses(query);
    assertEquals("String without spaces, so must be same", query, answer);
  }

  @Test
  public void replaceSpacesByPlusesMoreThanOneSpaceInSuccession() {
    String request = "String  with  two   or   three   spaces";
    ToshokanTracker tt = new ToshokanTracker();
    String formedRegexp = tt.replaceSpacesByPluses(request);
    assertEquals("String++with++two+++or+++three+++spaces", formedRegexp);
  }
}
