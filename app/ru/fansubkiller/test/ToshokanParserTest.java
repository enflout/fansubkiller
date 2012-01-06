package ru.fansubkiller.test;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.fansubkiller.content.SearchResult;
import ru.fansubkiller.search.ToshokanParser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author i.orlov
 */
public class ToshokanParserTest {
  private ToshokanParser parser;
  private DocumentBuilder documentBuilder;

  @Before
  public void setUp() throws ParserConfigurationException {
    parser = new ToshokanParser();
    documentBuilder = initDocumentBuilder();
  }

  private DocumentBuilder initDocumentBuilder() throws ParserConfigurationException {
    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    docBuilderFactory.setNamespaceAware(true);
    return docBuilderFactory.newDocumentBuilder();
  }

  @Test
  public void testConcatChildNodeValues() {
    Document doc = documentBuilder.newDocument();
    Element el = doc.createElement("a");
    el.appendChild(doc.createTextNode("First, "));
    el.appendChild(doc.createTextNode("second, "));
    el.appendChild(doc.createTextNode("third."));
    assertEquals("First, second, third.", parser.concatChildNodeValues(el));
  }

  @Test
  public void testParseToInnerFormat() {
    Document doc = documentBuilder.newDocument();
    Element root = doc.createElement("root");
    doc.appendChild(root);
    Element element = getXMLResult(doc, "http://firstSecond.ru", "First, ", "second, ", "third.");
    root.appendChild(element);
    Element element2 = getXMLResult(doc, "http://chiDe.ru", "Chip ", "and ", "Deil.");
    root.appendChild(element2);
    Element element3 = getXMLResult(doc, "http://oneTwoThree.ru", "1 ", "2 ", "3.");
    root.appendChild(element3);

    NodeList nodeList = root.getChildNodes();
    List<SearchResult> actualResults = parser.parseToInnerFormat(nodeList);
    List<SearchResult> expectedResults = new ArrayList<SearchResult>();
    addToExpected(expectedResults, "First, second, third.", "http://firstSecond.ru");
    addToExpected(expectedResults, "Chip and Deil.", "http://chiDe.ru");
    addToExpected(expectedResults, "1 2 3.", "http://oneTwoThree.ru");
    assertEquals(expectedResults, actualResults);
  }

  private Element getXMLResult(Document doc, String href, String... titles) {
    Element result = doc.createElement("a");
    result.setAttribute("href", href);
    for (String title : titles) {
      result.appendChild(doc.createTextNode(title));
    }
    return result;
  }

  private void addToExpected(List<SearchResult> expectedResults, String title, String href) {
    SearchResult oneExpectedResult = new SearchResult(title, href);
    expectedResults.add(oneExpectedResult);
  }
}
