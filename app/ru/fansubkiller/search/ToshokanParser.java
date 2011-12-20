package ru.fansubkiller.search;

import ru.fansubkiller.content.SearchResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author i.orlov
 */
public class ToshokanParser {
  private String pageText;

  public List<SearchResult> getResults(String pageText) {
    this.pageText = pageText;
    List<String> titles = getTitles();
    List<String> urls = getURLs();

    return formSearchResultCollection(titles, urls);
  }

  private List<String> getTitles() {
    List<String> result = new ArrayList<String>();
    String titleRegexp = "<a\\s+rel=\"nofollow\"\\s+type=\"application/x-bittorrent\"[^>]*>([^<]*)</a>";

    Pattern titlePattern = Pattern.compile(titleRegexp);
    Matcher titleMatcher = titlePattern.matcher(pageText);
    boolean isNextFinded = titleMatcher.find();
    while (isNextFinded) {
      result.add(titleMatcher.group(1));
      isNextFinded = titleMatcher.find();
    }

    return result;
  }

  private List<String> getURLs() {
    List<String> result = new ArrayList<String>();
    String urlsRegexp = "<a\\s+rel=\"nofollow\"\\s+type=\"application/x-bittorrent\"\\s+href=\"([^>]+)\"";
    Pattern urlsPattern = Pattern.compile(urlsRegexp);
    Matcher urlsMatcher =  urlsPattern.matcher(pageText);
    boolean isNextFinded = urlsMatcher.find();
    while (isNextFinded) {
      result.add(urlsMatcher.group(1));
      isNextFinded = urlsMatcher.find();
    }

    return result;
  }

  private List<SearchResult> formSearchResultCollection(List<String> titles, List<String> urls) {
    checkCollectionsEqualSize(titles, urls);
    ListIterator<String> titleIterator = titles.listIterator();
    ListIterator<String> urlsIterator = urls.listIterator();
    List<SearchResult> result = new ArrayList<SearchResult>();
    while (titleIterator.hasNext()) {
      String title = titleIterator.next();
      String url = urlsIterator.next();
      result.add(new SearchResult(title, url));
    }
    return result;
  }

  private void checkCollectionsEqualSize(Collection ... args) {
    for (int i = 0; i < args.length - 1; i++) {
      if (args[i].size() != args[i+1].size()) {
        throw new RuntimeException("Collection's sizes must be equals."
          + "first: " + args[i].size() + "; second: " + args[i+1].size());
      }
    }
  }
}
