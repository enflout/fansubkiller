package ru.fansubkiller.test;

import models.Task;
import org.junit.Test;
import ru.fansubkiller.notify.Checker;
import ru.fansubkiller.notify.CheckerImpl;

import static org.easymock.EasyMock.*;

/**
 * @author Ivan Orlov
 */

public class CheckerImplTest {
  @Test
  public void isTaskCheckingCalled() {
    Task taskMock = createMock(Task.class);
    Checker checker = new CheckerImpl(taskMock);
    replay();
    taskMock.execute();
    taskMock.isExistsResults();
    checker.start();
    verify();
  }
}
