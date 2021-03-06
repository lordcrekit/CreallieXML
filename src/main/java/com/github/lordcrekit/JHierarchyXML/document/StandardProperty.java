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

/**
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class StandardProperty extends StandardStructure implements XMLProperty {

    /**
     * Constructs a new StandardProperty.
     */
    StandardProperty(String name, String value) {
        super();
        setName(name);
        setValue(value);
    }

    @Override
    public XMLProperty setParent(XMLElement element) {
        if (getParent() != null)
            getParent().removeProperty(this);

        super.setParent(element);
        return this;
    }

    @Override
    public XMLProperty setName(String name) {
        super.setName(name);
        return this;
    }

    @Override
    public XMLProperty setValue(String value) {
        if (value == null)
            throw new NullPointerException("Properties cannot have null values!");
        super.setValue(value);
        return this;
    }

    @Override
    public String toString() {
        return getName() + "=\"" + getValue() + "\"";
    }
}
