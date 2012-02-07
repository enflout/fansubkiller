package ru.fansubkiller.test;

import ru.fansubkiller.search.Tracker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author i.orlov
 */
public class TrackerTest {
  @Test
  public void urlSpacesShouldBeTransformedToPluses() {
    String query = "слово слово слово слово   слово  слово словослово 64hgfh fghtr h rh 6h nbfghbf";
    String answer = Tracker.replaceSpacesByPluses(query);
    assertEquals("слово+слово+слово+слово+++слово++слово+словослово+64hgfh+fghtr+h+rh+6h+nbfghbf", answer);
  }

  @Test(expected=NullPointerException.class)
  public void inSpacesToPlusesNullStringNotAllowed() {
    String query = null;
    // must throw NPE
    Tracker.replaceSpacesByPluses(query);
  }

  @Test
  public void inSpacesToPlusesZeroLengthStringAllowed() {
    String query = "";
    String answer = Tracker.replaceSpacesByPluses(query);
    assertEquals("", answer);
  }

  @Test
  public void inSpacesToPlusesNoSpaceStringAllowed() {
    String query = "no_spaces_string";
    String answer = Tracker.replaceSpacesByPluses(query);
    assertEquals("String without spaces, so must be same", query, answer);
  }

  @Test
  public void inSpacesToPlusesMoreThanOneSpaceInSuccessionAllowed() {
    String request = "String  with  two   or   three   spaces";
    String formedRegexp = Tracker.replaceSpacesByPluses(request);
    assertEquals("String++with++two+++or+++three+++spaces", formedRegexp);
  }
}
