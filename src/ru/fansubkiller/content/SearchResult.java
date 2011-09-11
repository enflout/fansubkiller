package ru.fansubkiller.content;

import java.util.Date;

/**
 * @author i.orlov
 */
public class SearchResult {
  private String name;
  private String href;
  private Date added;

  public SearchResult(String name, String href) {
    this.name = name;
    this.href = href;
  }

  public SearchResult(String name, String href, Date added) {
    this.name = name;
    this.href = href;
    this.added = added;
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

  public Date getAdded() {
    return added;
  }

  public void setAdded(Date added) {
    this.added = added;
  }
}
