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
import Creallie.XML.document.CreaProperty;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class Writer {

    /*
     * ================================================ PRIMARY FUNCTIONS ===============================================
     */
    /**
     *
     * @param document
     * @param outstream
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void write( CreaDocument document, OutputStream outstream ) throws FileNotFoundException, IOException {
        write(document, new BufferedWriter(new OutputStreamWriter(outstream)));
    }

    /**
     *
     * @param document
     * @param writer
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void write( CreaDocument document, java.io.Writer writer ) throws FileNotFoundException, IOException {
        write(document, new BufferedWriter(writer));
    }

    /**
     *
     * @param document
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void write( CreaDocument document, File file ) throws FileNotFoundException, IOException {
        try ( FileOutputStream outstream = new FileOutputStream(file) ) {
            write(document, new BufferedWriter(new OutputStreamWriter(outstream)));
        }
    }

    /*
     * ================================================ PRIVATE FUNCTIONS ===============================================
     */
    private static void write( CreaDocument document, BufferedWriter writer ) throws FileNotFoundException, IOException {
        writer.write(getVersion());
        if ( document.getRootElement().exists() )
            recursion(writer, document.getRootElement());

        writer.flush();
    }

    private static void recursion( BufferedWriter writer, CreaElement element ) throws FileNotFoundException, IOException {
        writer.write("<");
        writer.write(element.getName());
        for ( CreaProperty prop : element.getProperties() )
            try {
                writer.write(" ");
                writer.write(prop.getName());
                writer.write("=\"");
                writer.write(escape_chars(prop.getValue()));
                writer.write("\"");
            } catch ( Exception ex ) {
                throw new RuntimeException("In property: " + prop.getName(), ex);
            }
        if ( (element.getValue() == null || element.getValue().isEmpty()) && element.getChildren().isEmpty() )
            writer.write(" />");
        else
            try {
                writer.write(">");
                if ( element.getValue() != null && !element.getValue().isEmpty() )
                    writer.write(escape_chars(element.getValue()));

                for ( CreaElement child : element.getChildren() )
                    recursion(writer, child);

                writer.write("</");
                writer.write(element.getName());
                writer.write(">");
            } catch ( Exception ex ) {
                throw new RuntimeException("In element: " + element.getName(), ex);
            }
    }

    private static String getVersion() {
        return "<?xml version=\"1.0\"?>";
    }

    private static String escape_chars( String out ) {
        return out.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;").replaceAll("<", "&lt;").replaceAll("&gt;", ">");
    }
}
