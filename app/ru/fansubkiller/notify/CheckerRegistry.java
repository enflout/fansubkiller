package ru.fansubkiller.notify;

import models.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Orlov
 */

public class CheckerRegistry {
  protected static Map<Long, Checker> checkers = new HashMap<Long, Checker>();

  public static Checker addChecker(Task task) {
    Checker checker = new CheckerImpl(task);
    checkers.put(task.id, checker);
    return checker;
  }

  public static Checker getChecker(Task task) {
    return checkers.get(task.id);
  }

  public static Checker reloadChecker(Task task) {
    removeChecker(task);
    return addChecker(task);
  }

  public static Checker removeChecker(Task task) {
    return checkers.remove(task.id);
  }

  public static void clear() {
    checkers = new HashMap<Long, Checker>();
  }

  public static int size() {
    return checkers.size();
  }
}
