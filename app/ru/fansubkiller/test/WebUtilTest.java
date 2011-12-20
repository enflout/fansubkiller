package ru.fansubkiller.test;

import org.junit.Test;
import ru.fansubkiller.web.WebUtil;

import static org.junit.Assert.assertNotNull;

/**
 * @author i.orlov
 */

public class WebUtilTest {
  @Test
  public void checkIsReturnNotNull() {
    assertNotNull(WebUtil.getHttpText("http://tokyotosho.info/"));
  }

  @Test(expected=RuntimeException.class)
  public void checkIsThrowExceptionForNotExistedPage() {
    WebUtil.getHttpText("some_fake_site_name");
  }
}
