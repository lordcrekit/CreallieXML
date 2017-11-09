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
package com.github.lordcrekit.JHierarchyXML.io;

import com.github.lordcrekit.JHierarchyXML.document.StandardDocument;
import com.github.lordcrekit.JHierarchyXML.document.XMLDocument;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testUnescapingPolicy() throws IOException {
        System.out.println("Unescaping policy");

        final Map<Character, String> escapeCharacters = new HashMap<>();
        escapeCharacters.put('&', "&amp;");
        escapeCharacters.put('\"', "&quot;");
        escapeCharacters.put('\'', "&apos;");
        escapeCharacters.put('<', "&lt;");
        escapeCharacters.put('>', "&gt;");

        for (final Map.Entry<Character, String> e : escapeCharacters.entrySet()) {
            final Character literal = e.getKey();
            final String escapeSequence = e.getValue();
            System.out.println('\t' + escapeSequence + " => " + literal);

            final String xml_element = "<doc><content>" + escapeSequence + "</content></doc>";
            final XMLDocument doc = Reader.read(new StandardDocument(), new StringReader(xml_element));
            assertEquals(literal.toString(), doc.getRootElement().getChild().getValue());
        }
    }

    @Test
    public void testCommentReading() throws IOException {
        System.out.println("Comment reading");

        final XMLDocument expected;
        final XMLDocument withComment;
        try (StringReader rdr = new StringReader("<doc><content/></doc>")) {
            expected = Reader.read(new StandardDocument(), rdr);
        }
        try (StringReader rdr = new StringReader("<doc><!-- Document comment --><content/></doc>")) {
            withComment = Reader.read(new StandardDocument(), rdr);
        }

        assertEquals(expected, withComment);
    }
}
