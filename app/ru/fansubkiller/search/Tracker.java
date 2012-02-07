package ru.fansubkiller.search;

import ru.fansubkiller.content.SearchResult;

import java.util.List;

/**
 * @author i.orlov
 */
public abstract class Tracker {
  public static String replaceSpacesByPluses(String withSpaces) {
    return withSpaces.replace(' ', '+');
  }

  public abstract List<SearchResult> search(String searchedText);
  public abstract String getSearchEngineURL();
  public abstract String formSearchURL(String searchedText);
}
