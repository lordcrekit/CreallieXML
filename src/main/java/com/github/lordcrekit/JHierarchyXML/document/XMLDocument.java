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
package lordcrekit.JHierarchyXML.document;

/**
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public interface XMLDocument {

    /**
     * Initialize a new XMLElement for this XMLDocument.
     *
     * @param name
     *         The name of the new XMLElement.
     * @return The new XMLElement.
     */
    XMLElement initElement(String name);

    /**
     * Initialize a new XMLElement for this XMLDocument.
     *
     * @param name
     *         The name of the new XMLElement.
     * @param value
     *         The text content value of the new XMLElement.
     * @return The new XMLElement.
     */
    XMLElement initElement(String name, String value);

    /**
     * Initialize a new XMLProperty for this XMLDocument.
     *
     * @param name
     *         The name of the new XMLProperty.
     * @param value
     *         The text content value for the new XMLProperty.
     * @return The new XMLProperty.
     */
    XMLProperty initProperty(String name, String value);

    /**
     * Get the root XMLElement of this XMLDocument.
     *
     * @return The root XMLElement of this XMLDocument.
     */
    XMLElement getRootElement();

    /**
     * Set the root XMLElement of this XMLDocument.
     *
     * @param element
     *         The new root XMLElement for this XMLDocument.
     * @return A pointer back to this XMLDocument.
     */
    XMLDocument setRootElement(XMLElement element);
}
