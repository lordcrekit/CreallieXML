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
package Creallie.XML.io;

import Creallie.XML.document.CreaDocument;
import Creallie.XML.document.CreaElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class Reader {

    /*
     * ================================================ PRIMARY FUNCTIONS ===============================================
     */
    /**
     *
     * @param document
     * @param instream
     * @return
     * @throws IOException
     */
    public static CreaDocument read( CreaDocument document, InputStream instream ) throws IOException {
        return read(document, new InputSource(instream));
    }

    /**
     *
     * @param document
     * @param reader
     * @return
     * @throws IOException
     */
    public static CreaDocument read( CreaDocument document, java.io.Reader reader ) throws IOException {
        return read(document, new InputSource(reader));
    }

    /**
     *
     * @param document
     * @param file
     * @return
     * @throws IOException
     */
    public static CreaDocument read( CreaDocument document, File file ) throws IOException {
        try ( FileInputStream instream = new FileInputStream(file) ) {
            return read(document, new InputSource(instream));
        }
    }

    /*
     * ================================================ PRIVATE FUNCTIONS ===============================================
     */
    private static CreaDocument read( CreaDocument document, InputSource source ) throws IOException {
        document.setRootElement(null);
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            DefaultHandler handler = new DefaultHandler() {

                CreaElement currentElement = null;
                StringBuilder curVal;

                @Override
                public void startElement( String uri, String localName, String qName, Attributes attributes ) throws SAXException {

                    CreaElement newEle = document.initElement(qName);
                    for ( int i = 0; i < attributes.getLength(); i++ )
                        newEle.addProperty(document.initProperty(attributes.getQName(i), attributes.getValue(i)));

                    if ( currentElement == null )
                        document.setRootElement(newEle);
                    else
                        currentElement.addChild(newEle);
                    currentElement = newEle;
                }

                @Override
                public void endElement( String uri, String localName, String qName ) throws SAXException {
                    currentElement.setValue(curVal == null ? null : curVal.toString());
                    curVal = new StringBuilder();
                    currentElement = currentElement.getParent();
                }

                @Override
                public void characters( char ch[], int start, int length ) throws SAXException {
                    // TODO : UNESCAPE CHARACTERS
                    char buf[] = new char[length];
                    for ( int i = 0; i < length; i++ )
                        buf[i] = ch[start + i];
                    if ( curVal == null )
                        curVal = new StringBuilder(new String(buf));
                    else
                        curVal.append(buf);
                }
            };
            parser.parse(source, handler);
        } catch ( ParserConfigurationException | SAXException ex ) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }
}
