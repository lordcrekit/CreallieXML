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
package com.github.lordcrekit.JHierarchyXML.document;

import java.util.Objects;

/**
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class StandardDocument implements XMLDocument {

    private XMLElement mRootElement = null;

    /**
     * Default constructor for StandardDocument.
     */
    public StandardDocument() {
    }

    /**
     * Construct a new StandardDocument with a root element of the given name.
     *
     * @param rootName
     *         The name for the root element.
     */
    public StandardDocument(String rootName) {
        mRootElement = initElement(rootName);
    }

    @Override
    public XMLElement initElement(String name) {
        return new StandardElement().setName(name);
    }

    @Override
    public XMLElement initElement(String name, String value) {
        return new StandardElement().setName(name).setValue(value);
    }

    @Override
    public XMLProperty initProperty(String name, String value) {
        return new StandardProperty(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof XMLDocument))
            return false;
        XMLDocument od = (XMLDocument) o;
        return this.getRootElement() == null
                ? od.getRootElement() == null
                : this.getRootElement().equals(od.getRootElement());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.mRootElement);
        return hash;
    }

    @Override
    public XMLElement getRootElement() {
        return (mRootElement == null ? NullElement.getInstance() : mRootElement);
    }

    @Override
    public XMLDocument setRootElement(XMLElement element) {
        mRootElement = element;
        return this;
    }

    @Override
    public String toString() {
        return "<document>\n" + mRootElement.toString() + "\n</document>";
    }
}
