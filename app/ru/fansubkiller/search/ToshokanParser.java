package ru.fansubkiller.search;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.fansubkiller.content.SearchResult;
import ru.fansubkiller.xml.XMLUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author i.orlov
 */
public class ToshokanParser {
  public List<SearchResult> getResults(URL url) {
    String xPathExpression = ".//div[@id='main']/table[2]//tr/td[2]/a";
    NodeList xmlResultTree = (NodeList) XMLUtil.getXMLResultTree(url, xPathExpression);
    return parseToInnerFormat(xmlResultTree);
  }

  public List<SearchResult> parseToInnerFormat(NodeList nodes) {
    List<SearchResult> results = new ArrayList<SearchResult>();
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      String title = concatChildNodeValues(node);
      String href = nodes.item(i).getAttributes().getNamedItem("href").getNodeValue();
      results.add(new SearchResult(title, href));
    }
    return results;
  }

  public String concatChildNodeValues(Node node) {
    NodeList childList = node.getChildNodes();
    String result = "";
    for (int i = 0; i < childList.getLength(); i++) {
      result += childList.item(i).getNodeValue();
    }
    return result;
  }
}
