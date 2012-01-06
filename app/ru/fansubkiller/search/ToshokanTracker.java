package ru.fansubkiller.search;

import ru.fansubkiller.content.SearchResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author i.orlov
 */
public class ToshokanTracker extends Tracker {
  public List<SearchResult> search(String searchRequest) {
    try {
      String pageHref = formSearchURL(searchRequest);
      URL url = new URL(pageHref);
      ToshokanParser parser = new ToshokanParser();
      return parser.getResults(url);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String formSearchURL(String searchRequest) {
    return getSearchEngineURL() + "?terms=" + replaceSpacesByPluses(searchRequest) + "&type=0&size_min=&size_max=&username=";
  }

  @Override
  public String getSearchEngineURL() {
    return "http://tokyotosho.info/search.php";
  }
}
