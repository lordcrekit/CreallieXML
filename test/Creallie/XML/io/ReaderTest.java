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
package Creallie.XML.io;

import Creallie.XML.document.CreaDocument;
import Creallie.XML.document.StandardDocument;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William
 */
public class ReaderTest {

    private static final File FILE = new File("test.xml");

    public ReaderTest() throws IOException {
        CreaDocument doc = new StandardDocument("TestingDoc");
        doc.getRootElement()
                .addChild(doc.initElement("first", "0"))
                .addChild(doc.initElement("second", "1")
                        .addProperty(doc.initProperty("atr01", "A")))
                .addChild(doc.initElement("third", "2")
                        .addChild(doc.initElement("inner_first", "a")
                                .addChild(doc.initElement("innner_inner_first", "_a"))));

        Writer.write(doc, FILE);
        CreaDocument afterDoc = Creallie.XML.io.Reader.read(new StandardDocument(), FILE);
        String before = doc.toString();
        String after = afterDoc.toString();
        System.out.println("BEFORE:\n" + before);
        System.out.println("AFTER:\n" + after);
        assertEquals(before, after);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        FILE.delete();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of read method, of class Reader.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRead_CreaDocument_InputStream() throws Exception {
//        System.out.println("read");
//        CreaDocument document = null;
//        InputStream instream = null;
//        CreaDocument expResult = null;
//        Reader.read(document, instream);
//        CreaDocument result = Reader.read(document, instream);
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
    public void testRead_CreaDocument_Reader() throws Exception {
//        System.out.println("read");
//        CreaDocument document = null;
//        Reader reader = null;
//        CreaDocument expResult = null;
//        CreaDocument result = Creallie.XML.io.Reader.read(document, reader);
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
    public void testRead_CreaDocument_File() throws Exception {
//        System.out.println("read");
//        CreaDocument document = null;
//        File file = null;
//        CreaDocument expResult = null;
//        CreaDocument result = Creallie.XML.io.Reader.read(document, file);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
