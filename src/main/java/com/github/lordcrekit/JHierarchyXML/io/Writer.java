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
package com.github.lordcrekit.JHierarchyXML.io;

import com.github.lordcrekit.JHierarchyXML.document.XMLDocument;
import com.github.lordcrekit.JHierarchyXML.document.XMLElement;
import com.github.lordcrekit.JHierarchyXML.document.XMLProperty;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class Writer {

    /**
     * Write the given XMLDocument to an OutputStream.
     *
     * @param document
     *         The document to write.
     * @param outstream
     *         The stream to write to.
     * @throws IOException
     *         If something goes wrong writing to the stream.
     */
    public static void write(XMLDocument document, OutputStream outstream) throws IOException {
        write(document, new BufferedWriter(new OutputStreamWriter(outstream)));
    }

    /**
     * Write the given XMLDocument to a Writer.
     *
     * @param document
     *         The document to write.
     * @param writer
     *         The writer to write to.
     * @throws IOException
     *         If something goes wrong writing to the writer.
     */
    public static void write(XMLDocument document, java.io.Writer writer) throws IOException {
        write(document, new BufferedWriter(writer));
    }

    /**
     * Write the given XMLDocument to a File.
     *
     * @param document
     *         The XMLDocument to write.
     * @param file
     *         The File to write to.
     * @throws IOException
     *         If something goes wrong writing to file.
     * @deprecated Use {@link #write(XMLDocument, Path)}
     */
    @Deprecated
    public static void write(XMLDocument document, File file) throws IOException {
        try (FileOutputStream outstream = new FileOutputStream(file)) {
            write(document, new BufferedWriter(new OutputStreamWriter(outstream)));
        }
    }

    /**
     * Write the given XMLDocument to a Filepath.
     *
     * @param document
     *         The XMLDocument to write.
     * @param path
     *         The path to write it to.
     * @throws IOException
     *         If something goes wrong writing to the filepath.
     */
    public static void write(XMLDocument document, Path path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            write(document, writer);
        }
    }

    private static void write(XMLDocument document, BufferedWriter writer) throws IOException {
        writer.write(getVersion());
        if (document.getRootElement().exists())
            recursion(writer, document.getRootElement());

        writer.flush();
    }

    private final static void recursion(BufferedWriter writer, XMLElement element) throws IOException {
        writer.write('<');
        writer.write(element.getName());
        for (XMLProperty prop : element.getProperties())
            try {
                writer.write(' ');
                writer.write(prop.getName());
                writer.write("=\"");
                writer.write(escape_chars(prop.getValue()));
                writer.write('\"');
            } catch (Exception ex) {
                throw new RuntimeException("In property: " + prop.getName(), ex);
            }
        if ((element.getValue() == null || element.getValue().isEmpty())
                && element.getComments().isEmpty()
                && element.getChildren().isEmpty())
            writer.write(" />");
        else
            try {
                writer.write('>');
                for (String comment : element.getComments()) {
                    writer.write("<!-- ");
                    writer.write(escape_chars(comment));
                    writer.write(" -->");
                }

                if (element.getValue() != null && !element.getValue().isEmpty())
                    writer.write(escape_chars(element.getValue()));

                for (XMLElement child : element.getChildren())
                    recursion(writer, child);

                writer.write("</");
                writer.write(element.getName());
                writer.write('>');
            } catch (Exception ex) {
                throw new RuntimeException("In element: " + element.getName(), ex);
            }
    }

    private static String getVersion() {
        return "<?xml version=\"1.0\"?>";
    }

    private static String escape_chars(String out) {
        return out
                .replaceAll("&", "&amp;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&apos;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }
}
