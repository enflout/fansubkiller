package ru.fansubkiller.search;

import ru.fansubkiller.content.SearchResult;
import ru.fansubkiller.web.WebUtil;

import java.util.List;

/**
 * @author i.orlov
 */
public class ToshokanTracker extends Tracker {
  public List<SearchResult> search(String searchRequest) {
    String pageHref = formSearchURL(searchRequest);
    String pageText = WebUtil.getHttpText(pageHref);
    ToshokanParser parser = new ToshokanParser();
    return parser.getResults(pageText);
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
