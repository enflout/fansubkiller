package ru.fansubkiller.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author i.orlov
 */

/* todo: нет finally блока, не нравится текущая реализация */
public class WebUtil {
  public static String getHttpText(String href) {
    StringBuilder result = new StringBuilder();
    try {
      URL url = new URL(href);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String str;
      while ((str = in.readLine()) != null) {
        result.append(str);
      }
      in.close();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return result.toString();
  }
}
