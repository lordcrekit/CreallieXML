/*
 * The MIT License
 *
 * Copyright 2015 William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu).
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

import lordcrekit.JHierarchyXML.document.XMLDocument;
import lordcrekit.JHierarchyXML.document.XMLElement;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class Reader {

    /**
     * Populate a XMLDocument from an InputStream.
     *
     * @param document
     *         An empty XMLDocument to fill with content. Required to create Elements/Properties.
     * @param instream
     *         The stream to read from.
     * @return A pointer to the given XMLDocument.
     * @throws IOException
     *         If something goes wrong while reading from the stream.
     */
    public static XMLDocument read(XMLDocument document, InputStream instream) throws IOException {
        return read(document, new InputSource(instream));
    }

    /**
     * Populate a XMLDocument from a Reader.
     *
     * @param document
     *         An empty XMLDocument to fill with content. Required to create Elements/Properties.
     * @param reader
     *         The reader to read from.
     * @return A pointer to the given XMLDocument.
     * @throws IOException
     *         If something goes wrong while reading from the Reader.
     */
    public static XMLDocument read(XMLDocument document, java.io.Reader reader) throws IOException {
        return read(document, new InputSource(reader));
    }

    /**
     * Populate a XMLDocument from a parsed File.
     *
     * @param document
     *         An empty XMLDocument to fill with content. Required to create Elements/Properties.
     * @param file
     *         Path to the file to read.
     * @return A pointer to the given XMLDocument.
     * @throws IOException
     *         If something goes wrong while reading the File.
     * @deprecated Use {@link #read(XMLDocument, Path)}.
     */
    @Deprecated
    public static XMLDocument read(XMLDocument document, File file) throws IOException {
        try (FileInputStream instream = new FileInputStream(file)) {
            return read(document, new InputSource(instream));
        }
    }

    /**
     * Populate a XMLDocument from a parsed File.
     *
     * @param document
     *         An empty XMLDocument to fill with content. Required to create Elements/Properties.
     * @param path
     *         Path to the file to read.
     * @return A pointer to the given XMLDocument.
     * @throws IOException
     *         If something goes wrong while reading from the Path.
     */
    public static XMLDocument read(XMLDocument document, Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return read(document, reader);
        }
    }

    private static XMLDocument read(XMLDocument document, InputSource source) throws IOException {
        document.setRootElement(null);
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            DefaultHandler handler = new DefaultHandler() {

                XMLElement currentElement = null;
                StringBuilder curVal = null;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

                    // XMLte a new Element.
                    XMLElement newEle = document.initElement(qName);
                    // Add any attributes to the new Element.
                    for (int i = 0; i < attributes.getLength(); i++)
                        newEle.addProperty(document.initProperty(attributes.getQName(i), attributes.getValue(i)));

                    if (curVal != null)
                        currentElement.setValue(curVal.toString());
                    curVal = new StringBuilder();
                    if (currentElement == null)
                        document.setRootElement(newEle);
                    else
                        currentElement.addChild(newEle);
                    currentElement = newEle;
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (curVal != null) {
                        currentElement.setValue(curVal == null ? null : curVal.toString());
                        curVal = null;
                    }
                    currentElement = currentElement.getParent();
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    assert currentElement != null;
                    curVal = currentElement.getValue() == null
                            ? new StringBuilder()
                            : new StringBuilder(currentElement.getValue());
                    curVal.append(new String(ch, start, length).trim());
                    currentElement.setValue(curVal.toString());
                }
            };
            parser.parse(source, handler);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }
}
