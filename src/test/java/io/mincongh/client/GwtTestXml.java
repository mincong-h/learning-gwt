package io.mincongh.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.Text;
import com.google.gwt.xml.client.XMLParser;
import io.mincongh.shared.R;

/**
 * Extensible Markup Language (XML) is a data format commonly used
 * in modern web applications. XML uses custom tags to describe data
 * and is encoded as plain text, making it both flexible and easy to
 * work with. The GWT class library contains a set of types designed
 * for processing XML data.
 *
 * @author Mincong Huang
 */
public class GwtTestXml extends GWTTestCase {

  private static final String XML = "<?xml version=\"1.0\" ?>\n"
      + "<message>\n"
      + "  <header>\n"
      + "    <to name=\"Richard\" address=\"rick@school.edu\" />\n"
      + "    <from name=\"Joyce\" address=\"joyce@website.com\" />\n"
      + "    <sent>2007-05-12T12:03:55Z</sent>\n"
      + "    <subject>Re: Flight info</subject>\n"
      + "  </header>\n"
      + "  <body>I'll pick you up at the airport at 8:30. See you then!</body>\n"
      + "</message>";

  @Override
  public String getModuleName() {
    return R.JUNIT_MODULE;
  }

  /**
   * Tests how to parse a simple xml content using different methods.
   */
  public void testParseXml() throws Exception {
    Document message = XMLParser.parse(XML);

    // from
    Node fromNode = message.getElementsByTagName("from").item(0);
    String fromName = ((Element) fromNode).getAttribute("name");
    String fromAddress = ((Element) fromNode).getAttribute("address");
    assertEquals("Joyce", fromName);
    assertEquals("joyce@website.com", fromAddress);

    // subject
    String subject = message.getElementsByTagName("subject").item(0).getFirstChild().getNodeValue();
    assertEquals("Re: Flight info", subject);

    // body
    Text bodyNode = (Text) message.getElementsByTagName("body").item(0).getFirstChild();
    assertEquals("I'll pick you up at the airport at 8:30. See you then!", bodyNode.getData());
  }

  /**
   * Tests XML creation, which matches the original string
   * {@link #XML}.
   */
  public void testCreateXml() throws Exception {
    Document xml = XMLParser.createDocument();

    // header
    Element to = xml.createElement("to");
    to.setAttribute("address", "rick@school.edu");
    to.setAttribute("name", "Richard");

    Element from = xml.createElement("from");
    from.setAttribute("address", "joyce@website.com");
    from.setAttribute("name", "Joyce");

    Element sent = xml.createElement("sent");
    Text sentText = xml.createTextNode("2007-05-12T12:03:55Z");
    sent.appendChild(sentText);

    Element subject = xml.createElement("subject");
    Text subjectText = xml.createTextNode("Re: Flight info");
    subject.appendChild(subjectText);

    Element header = xml.createElement("header");
    header.appendChild(to);
    header.appendChild(from);
    header.appendChild(sent);
    header.appendChild(subject);

    // body
    Element body = xml.createElement("body");
    Text bodyText = xml.createTextNode("I'll pick you up at the airport at 8:30. See you then!");
    body.appendChild(bodyText);

    // assemble
    Element message = xml.createElement("message");
    message.appendChild(header);
    message.appendChild(body);
    String actual = message.toString();

    // assert
    Document ref = XMLParser.parse(XML);
    XMLParser.removeWhitespace(ref);
    String expected = ref.toString();
    assertEquals(expected, actual);
  }

}
