package ru.fansubkiller.search;

import ru.fansubkiller.content.SearchResult;

import java.net.URL;
import java.util.List;

/**
 * @author Ivan Orlov
 */

public interface Parser {
  List<SearchResult> getResults(URL url);
}
