package ru.fansubkiller.test;

import ru.fansubkiller.content.SearchResult;
import ru.fansubkiller.search.ToshokanTracker;
import ru.fansubkiller.search.Tracker;

import org.junit.Test;
import static org.junit.Assert.*;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author i.orlov
 */

public class ToshokanTrackerTest {
  @Test
  public void testSearch() {
    Tracker trackerMock = EasyMock.createMock(Tracker.class);
    List<SearchResult> fakeResult = new ArrayList<SearchResult>();
    fakeResult.add(new SearchResult("Renpika-01", "http://some.su"));
    fakeResult.add(new SearchResult("Renpika-ren-01", "http://fare.gs"));

    EasyMock.expect(trackerMock.search(EasyMock.anyObject(String.class))).andReturn(fakeResult);
    EasyMock.replay(trackerMock);
    List<SearchResult> result = trackerMock.search("Renpika 01 mkv");

    EasyMock.verify(trackerMock);
  }

  @Test
  public void testCorrectnessSearchResults() {
    Tracker tracker = new ToshokanTracker();
    tracker.search("Renpika 01");
  }

  @Test
  public void formSearchURL() {
    String searchText = "Harry Potter 03";
    ToshokanTracker tracker = new ToshokanTracker();
    String searchURL = tracker.formSearchURL(searchText);
    assertEquals("http://tokyotosho.info/search.php?terms=Harry+Potter+03&type=0&size_min=&size_max=&username=", searchURL);
  }
}
