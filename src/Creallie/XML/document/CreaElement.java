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
import java.util.List;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public interface CreaElement extends CreaStructure {

    
    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    /**
     *
     * @param element
     * @return
     */
    @Override
    public CreaElement setParent( CreaElement element );
    
    /**
     *
     * @param filters
     * @return
     */
    public CreaElement getChild( CreaDocumentFilter... filters );

    /**
     *
     * @param filters
     * @return
     */
    public List<CreaElement> getChildren( CreaDocumentFilter... filters );

    /**
     *
     * @param element
     * @return
     */
    public CreaElement addChild( CreaElement element );

    /**
     *
     * @param element
     * @return
     */
    public CreaElement removeChild( CreaElement element );

    
    /**
     * 
     * @param filters
     * @return 
     */
    public CreaProperty getProperty(CreaDocumentFilter... filters);
    
    /**
     * 
     * @param filters
     * @return 
     */
    public List<CreaProperty> getProperties(CreaDocumentFilter... filters);
    
    /**
     *
     * @param property
     * @return
     */
    public CreaElement addProperty( CreaProperty property );

    /**
     *
     * @param property
     * @return
     */
    public CreaElement removeProperty( CreaProperty property );

    /**
     *
     * @param name
     * @return
     */
    @Override
    public CreaElement setName( String name );

    /**
     *
     * @param value
     * @return
     */
    @Override
    public CreaElement setValue( String value );

    /*
     * ================================================ VISUAL FUNCTIONS ================================================
     */
    public StringBuilder toString(StringBuilder strb, int indents);
}
