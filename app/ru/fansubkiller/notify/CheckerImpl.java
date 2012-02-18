package ru.fansubkiller.notify;

import models.Task;

/**
 * @author Ivan Orlov
 */

public class CheckerImpl implements Checker, Runnable {
  private Task task;
  private boolean isRunning;

  public CheckerImpl(Task task) {
    this.task = task;
    this.isRunning = true;
    start();
  }

  public void run() {
    while (isRunning) {
      execute();
      tryToSleep(task.timeout);
    }
  }

  public void execute() {
    task.execute();
  }

  private void tryToSleep(Short minutes) {
    try {
      Thread.sleep(minutesToMs(minutes));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private Long minutesToMs(Short minutes) {
    return (long)minutes * 60 * 1000;
  }

  public void start() {
    Thread thread = new Thread(this);
    thread.start();
  }

  public void stop() {
    isRunning = false;
  }
}
