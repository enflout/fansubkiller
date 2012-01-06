package controllers;

import play.mvc.Controller;
import ru.fansubkiller.content.SearchResult;
import ru.fansubkiller.search.ToshokanTracker;
import ru.fansubkiller.search.Tracker;

import models.Task;

import java.util.List;

public class Application extends Controller {

  public static void index() {
    List<Task> tasks = Tasks.getTasks();
    render(tasks);
  }

  public static void search(String searchText) {
  	List<Task> tasks = Tasks.getTasks();
    if (searchText == null || "".equals(searchText)) {
      render(tasks);
    }
    Tracker tracker = new ToshokanTracker();
    List<SearchResult> results = tracker.search(searchText);
    render(results, tasks);
  }

}