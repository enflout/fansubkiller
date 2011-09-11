package ru.fansubkiller.search;

import ru.fansubkiller.content.SearchResult;

import java.util.List;

/**
 * @author i.orlov
 */
public class ToshokanTracker extends Tracker {

  public List<SearchResult> search(String searchText) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
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
