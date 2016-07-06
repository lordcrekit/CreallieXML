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

import java.util.Objects;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class StandardDocument implements CreaDocument {

	/*
     * ================================================ MEMBER VARIABLES ================================================
	 */
	private CreaElement mRootElement = null;

	/*
     * ================================================== CONSTRUCTORS ==================================================
	 */
	/**
	 * Default constructor for StandardDocument.
	 */
	public StandardDocument() {
	}

	/**
	 *
	 * @param rootName
	 */
	public StandardDocument( String rootName ) {
		mRootElement = initElement(rootName);
	}

	/*
     * ================================================ PRIMARY FUNCTIONS ===============================================
	 */
	@Override
	public CreaElement initElement( String name ) {
		return new StandardElement().setName(name);
	}

	@Override
	public CreaElement initElement( String name, String value ) {
		return new StandardElement().setName(name).setValue(value);
	}

	@Override
	public CreaProperty initProperty( String name, String value ) {
		return new StandardProperty(name, value);
	}

	@Override
	public boolean equals( Object o ) {
		if ( !(o instanceof CreaDocument) )
			return false;
		CreaDocument od = (CreaDocument) o;
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

	/*
     * =============================================== GETTERS AND SETTERS ==============================================
	 */
	@Override
	public CreaElement getRootElement() {
		return (mRootElement == null ? NullElement.getInstance() : mRootElement);
	}

	@Override
	public CreaDocument setRootElement( CreaElement element ) {
		mRootElement = element;
		return this;
	}

	/*
     * ================================================ VISUAL FUNCTIONS ================================================
	 */
	@Override
	public String toString() {
		return "<document>\n" + mRootElement.toString() + "\n</document>";
	}

	/*
     * ================================================ PRIVATE FUNCTIONS ===============================================
	 */
}
