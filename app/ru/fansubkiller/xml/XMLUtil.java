package ru.fansubkiller.xml;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ivan Orlov
 */

public class XMLUtil {
  public static Object getXMLResultTree(URL url, String xPathExpression) {
    InputStream in = null;
    try {
      in = url.openStream();
      Document doc = getValidXHTMLDocument(in);
      return applyXPathExpression(doc, xPathExpression);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      tryToCloseStream(in);
    }
    throw new RuntimeException("Не удалось обработать документ");
  }

  public static Document getValidXHTMLDocument(InputStream is) {
    Tidy tidy = new Tidy();
    tidy.setXHTML(true);
    return tidy.parseDOM(is, null);
  }

  public static Object applyXPathExpression(Document doc, String expressionText)  {
    try {
      XPath xpath = XPathFactory.newInstance().newXPath();
      XPathExpression expression = xpath.compile(expressionText);
      return expression.evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException e) {
      throw new RuntimeException(e);
    }
  }

  private static void tryToCloseStream(InputStream is) {
    try {
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
