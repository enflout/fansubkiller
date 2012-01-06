package ru.fansubkiller.content;

/**
 * @author i.orlov
 */
public class SearchResult {
  private String name;
  private String href;

  public SearchResult(String name, String href) {
    this.name = name;
    this.href = href;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 37 * result + name.hashCode();
    result = 37 * result + href.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof SearchResult)) {
      return false;
    }
    SearchResult sr = (SearchResult) o;
    return name.equals(sr.name) && href.equals(sr.href);
  }

  @Override
  public String toString() {
    return name + " - " + href;
  }

}
