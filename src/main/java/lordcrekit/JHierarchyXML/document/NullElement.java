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

import lordcrekit.JHierarchyXML.filter.XMLDocumentFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class NullElement extends NullStructure implements XMLElement {

    private final static NullElement mInstance = new NullElement();

    /**
     * Default constructor for NullElement.
     */
    private NullElement() {
        super();
    }

    /**
     *
     * @return
     */
    public static NullElement getInstance() {
        return mInstance;
    }

    @Override
    public XMLElement setParent( XMLElement element ) {
        return NullElement.getInstance();
    }

    @Override
    public XMLElement getChild( XMLDocumentFilter... filters ) {
        return NullElement.getInstance();
    }

    @Override
    public List<XMLElement> getChildren( XMLDocumentFilter... filters ) {
        return new ArrayList<>();
    }

    @Override
    public XMLElement addChild( XMLElement element ) {
        return NullElement.getInstance();
    }

    @Override
    public XMLElement removeChild( XMLElement element ) {
        return NullElement.getInstance();
    }

    @Override
    public XMLProperty getProperty( XMLDocumentFilter... filters ) {
        return NullProperty.getInstance();
    }

    @Override
    public List<XMLProperty> getProperties( XMLDocumentFilter... filters ) {
        return new ArrayList<>();
    }

    @Override
    public XMLElement addProperty( XMLProperty property ) {
        return NullElement.getInstance();
    }

    @Override
    public XMLElement removeProperty( XMLProperty property ) {
        return NullElement.getInstance();
    }

    @Override
    public XMLElement setName( String name ) {
        return NullElement.getInstance();
    }

    @Override
    public XMLElement setValue( String value ) {
        return NullElement.getInstance();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Todo");    // TODO: THIS
    }

    @Override
    public StringBuilder toString( StringBuilder strb, int indents ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
