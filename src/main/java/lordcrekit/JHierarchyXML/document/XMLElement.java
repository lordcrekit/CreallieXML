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
import java.util.List;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public interface XMLElement extends XMLStructure {

    /**
     *
     * @param element
     * @return
     */
    @Override
    XMLElement setParent( XMLElement element );
    
    /**
     *
     * @param filters
     * @return
     */
    XMLElement getChild( XMLDocumentFilter... filters );

    /**
     *
     * @param filters
     * @return
     */
    List<XMLElement> getChildren( XMLDocumentFilter... filters );

    /**
     *
     * @param element
     * @return
     */
    XMLElement addChild( XMLElement element );

    /**
     *
     * @param element
     * @return
     */
    XMLElement removeChild( XMLElement element );

    
    /**
     * 
     * @param filters
     * @return 
     */
    XMLProperty getProperty(XMLDocumentFilter... filters);
    
    /**
     * 
     * @param filters
     * @return 
     */
    List<XMLProperty> getProperties(XMLDocumentFilter... filters);
    
    /**
     *
     * @param property
     * @return
     */
    XMLElement addProperty( XMLProperty property );

    /**
     *
     * @param property
     * @return
     */
    XMLElement removeProperty( XMLProperty property );

    /**
     *
     * @param name
     * @return
     */
    @Override
    XMLElement setName( String name );

    /**
     *
     * @param value
     * @return
     */
    @Override
    XMLElement setValue( String value );

    StringBuilder toString(StringBuilder strb, int indents);
}
