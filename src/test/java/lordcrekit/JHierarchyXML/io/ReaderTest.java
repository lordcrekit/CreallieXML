/*
 * The MIT License
 *
 * Copyright 2016 William.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package lordcrekit.JHierarchyXML.io;

import lordcrekit.JHierarchyXML.document.StandardDocument;
import lordcrekit.JHierarchyXML.document.XMLDocument;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * @author William
 */
public class ReaderTest {

    private static Path FILE = null;

    public ReaderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        System.out.println(Reader.class.getName());
        FILE = Files.createTempDirectory("XML-Tests");
    }

    @AfterClass
    public static void tearDownClass() throws IOException {
        Files.delete(FILE);
    }

    @Test
    public void testIO() throws IOException {
        System.out.println("test IO");
        XMLDocument doc = new StandardDocument("TestingDoc");
        doc.getRootElement()
                .addChild(doc.initElement("first", "0"))
                .addChild(doc.initElement("second", "1")
                        .addProperty(doc.initProperty("atr01", "A"))
                ).addChild(doc.initElement("third", "2")
                .addChild(doc.initElement("inner_first", "a")
                        .addChild(doc.initElement("innner_inner_first", "_a"))
                )
        );

        Path xml_path = Paths.get(FILE.toString(), "example.xml");
        try {
            Writer.write(doc, xml_path);
            XMLDocument afterDoc = Reader.read(new StandardDocument(), xml_path);
            String before = doc.toString();
            String after = afterDoc.toString();
            assertEquals(before, after);
        } finally {
            Files.delete(xml_path);
        }
    }

    @Test
    public void testWhitespacePolicy() throws IOException, URISyntaxException {
        System.out.println("test Whitespace Policy");


        for (Path p : Files.newDirectoryStream(
                Paths.get(ReaderTest.class.getResource("whitespace").toURI()))) {

            System.out.println("\t" + p.getFileName());
            XMLDocument expected = Reader.read(new StandardDocument(), Paths.get(p.toString(), "expected.xml"));
            XMLDocument got = Reader.read(new StandardDocument(), Paths.get(p.toString(), "got.xml"));
            assertEquals(String.format("===Expected===\n%s\n\n===Got===\n%s\n", expected, got),
                    true, expected.equals(got));
        }
    }

    /**
     * Test to make sure characters are properly un-escaped.
     */
    @Test
    public void testUnescapingPolicy() {
    }

    /**
     * Test of read method, of class Reader.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRead_XMLDocument_InputStream() throws Exception {
        System.out.println("test Read(XMLDocument, InputStream)");
//        System.out.println("read");
//        XMLDocument document = null;
//        InputStream instream = null;
//        XMLDocument expResult = null;
//        Reader.read(document, instream);
//        XMLDocument result = Reader.read(document, instream);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class Reader.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRead_XMLDocument_Reader() throws Exception {
        System.out.println("test Read(XMLDocument, Reader)");
//        System.out.println("read");
//        XMLDocument document = null;
//        Reader reader = null;
//        XMLDocument expResult = null;
//        XMLDocument result = Reader.read(document, reader);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class Reader.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRead_XMLDocument_File() throws Exception {
        System.out.println("test Read(XMLDocument, File)");
//        System.out.println("read");
//        XMLDocument document = null;
//        File file = null;
//        XMLDocument expResult = null;
//        XMLDocument result = Reader.read(document, file);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
