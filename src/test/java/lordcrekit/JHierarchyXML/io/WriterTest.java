package lordcrekit.JHierarchyXML.io;

import lordcrekit.JHierarchyXML.document.StandardDocument;
import lordcrekit.JHierarchyXML.document.XMLDocument;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WriterTest {

    public WriterTest() {
        System.out.println(Writer.class.getName());
    }

    @Test
    public void testUnescapingPolicy() throws IOException {
        System.out.println("Escaping policy");

        final Map<Character, String> escapeCharacters = new HashMap<>();
        escapeCharacters.put('&', "&amp;");
        escapeCharacters.put('\"', "&quot;");
        escapeCharacters.put('\'', "&apos;");
        escapeCharacters.put('<', "&lt;");
        escapeCharacters.put('>', "&gt;");

        for (final Map.Entry<Character, String> e : escapeCharacters.entrySet()) {
            final Character literal = e.getKey();
            final String escapeSequence = e.getValue();
            System.out.println('\t' + literal.toString() + " => " + escapeSequence);


            StandardDocument doc = new StandardDocument("doc");
            doc.getRootElement().addChild(doc.initElement("content", literal.toString()));

            StringWriter stringWriter = new StringWriter();
            Writer.write(doc, stringWriter);

            final String xml_element = "<?xml version=\"1.0\"?><doc><content>" + escapeSequence + "</content></doc>";
            assertEquals(xml_element, stringWriter.toString());
        }
    }
}
