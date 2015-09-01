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

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class NullProperty extends NullStructure implements CreaProperty {

    /*
     * ================================================ STATIC VARIABLES ================================================
     */
    private final static NullProperty mInstance = new NullProperty();

    /*
     * ======================================== CONSTRUCTORS & INSTANCE FUNCTIONS =======================================
     */
    /**
     * Default constructor for NullElement.
     */
    private NullProperty() {
        super();
    }

    /**
     *
     * @return
     */
    public static NullProperty getInstance() {
        return mInstance;
    }

    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    @Override
    public CreaProperty setParent( CreaElement element ) {
        return NullProperty.getInstance();
    }

    @Override
    public CreaProperty setName( String name ) {
        return NullProperty.getInstance();
    }

    @Override
    public CreaProperty setValue( String value ) {
        return NullProperty.getInstance();
    }

    /*
     * ================================================ VISUAL FUNCTIONS ================================================
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Todo");    // TODO: THIS
    }
}
