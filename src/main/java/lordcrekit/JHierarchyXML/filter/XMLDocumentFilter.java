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
package lordcrekit.JHierarchyXML.filter;

import lordcrekit.JHierarchyXML.document.XMLElement;
import lordcrekit.JHierarchyXML.document.XMLProperty;

/**
 * Defines a filter for elements and properties in a XMLDocument. When passed to associate search and get functions,
 * will filter out the returned structures.
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public interface XMLDocumentFilter {

    /**
     * Filter method for XMLElements.
     *
     * @param element
     *         The element that is being tested.
     * @return <code>true </code> if the XMLElement should be accepted, otherwise <code>false</code>.
     */
    boolean accepts(final XMLElement element);

    /**
     * Filter method for XMLProperties.
     *
     * @param property
     *         The property that is being tested.
     * @return <code>true</code>if the XMLProperty should be accepted, otherwise <code>false</code>.
     */
    boolean accepts(final XMLProperty property);
}
