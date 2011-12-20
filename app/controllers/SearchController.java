package controllers;

import play.mvc.Controller;
import ru.fansubkiller.content.SearchResult;
import ru.fansubkiller.search.ToshokanTracker;
import ru.fansubkiller.search.Tracker;

import java.util.List;

/**
 * @author i.orlov
 */

//  todo: Перенести в Application.

public class SearchController extends Controller {

  public static void index(String searchText) {
    if (searchText == null || "".equals(searchText)) {
      render();
    }
    Tracker tracker = new ToshokanTracker();
    List<SearchResult> results = tracker.search(searchText);
    render(results);
  }
}
