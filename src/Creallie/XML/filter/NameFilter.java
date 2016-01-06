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
package Creallie.XML.filter;

import Creallie.XML.document.CreaElement;
import Creallie.XML.document.CreaProperty;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class NameFilter implements CreaDocumentFilter {

    /*
     * ================================================ MEMBER VARIABLES ================================================
     */
    private final String mName;

    /*
     * ================================================== CONSTRUCTORS ==================================================
     */
    /**
     * Constructors a new NameFilter. Accepts any element or property with a matching name.
     *
     * @param name <code>{@link String }</code>: The name that must be matched.
     */
    public NameFilter( String name ) {
        mName = name;
    }

    /*
     * ================================================ PRIMARY FUNCTIONS ===============================================
     */
    @Override
    public boolean accepts( CreaElement element ) {
        return (element.getName() == null ? mName == null : element.getName().equals(mName));
    }

    @Override
    public boolean accepts( CreaProperty property ) {
        return (property.getName() == null ? mName == null : property.getName().equals(mName));
    }
}
