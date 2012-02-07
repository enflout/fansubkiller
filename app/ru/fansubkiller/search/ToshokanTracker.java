package ru.fansubkiller.search;

import ru.fansubkiller.content.SearchResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * @author i.orlov
 */
public class ToshokanTracker extends Tracker {
  public List<SearchResult> search(String searchedText) {
    try {
      String pageHref = formSearchURL(searchedText);
      URL url = new URL(pageHref);
      Parser parser = new ToshokanParser();
      return parser.getResults(url);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  @Override
  public String formSearchURL(String searchedText) {
    return getSearchEngineURL() + "?terms=" + replaceSpacesByPluses(searchedText) + "&type=0&size_min=&size_max=&username=";
  }

  @Override
  public String getSearchEngineURL() {
    return "http://tokyotosho.info/search.php";
  }
}
