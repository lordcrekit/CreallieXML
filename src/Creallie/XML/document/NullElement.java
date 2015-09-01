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
package Creallie.XML.document;

import Creallie.XML.filter.CreaDocumentFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class NullElement extends NullStructure implements CreaElement {

    /*
     * ================================================ STATIC VARIABLES ================================================
     */
    private final static NullElement mInstance = new NullElement();

    /*
     * ======================================== CONSTRUCTORS & INSTANCE FUNCTIONS =======================================
     */
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

    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    @Override
    public CreaElement setParent( CreaElement element ) {
        return NullElement.getInstance();
    }

    @Override
    public CreaElement getChild( CreaDocumentFilter... filters ) {
        return NullElement.getInstance();
    }

    @Override
    public List<CreaElement> getChildren( CreaDocumentFilter... filters ) {
        return new ArrayList<>();
    }

    @Override
    public CreaElement addChild( CreaElement element ) {
        return NullElement.getInstance();
    }

    @Override
    public CreaElement removeChild( CreaElement element ) {
        return NullElement.getInstance();
    }

    @Override
    public CreaProperty getProperty( CreaDocumentFilter... filters ) {
        return NullProperty.getInstance();
    }

    @Override
    public List<CreaProperty> getProperties( CreaDocumentFilter... filters ) {
        return new ArrayList<>();
    }

    @Override
    public CreaElement addProperty( CreaProperty property ) {
        return NullElement.getInstance();
    }

    @Override
    public CreaElement removeProperty( CreaProperty property ) {
        return NullElement.getInstance();
    }

    @Override
    public CreaElement setName( String name ) {
        return NullElement.getInstance();
    }

    @Override
    public CreaElement setValue( String value ) {
        return NullElement.getInstance();
    }

    /*
     * ================================================ VISUAL FUNCTIONS ================================================
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Todo");    // TODO: THIS
    }
}
