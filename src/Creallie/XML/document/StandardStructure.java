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
public abstract class StandardStructure implements CreaStructure {

	/*
     * ================================================ MEMBER VARIABLES ================================================
	 */
	private CreaElement mParent;
	private String mName;
	private String mValue;

	/*
     * ================================================== CONSTRUCTORS ==================================================
	 */
	/**
	 * Default constructor for StandardStructure.
	 */
	protected StandardStructure() {
	}

	/*
     * ================================================ PRIMARY FUNCTIONS ===============================================
	 */
	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public boolean equals( Object o ) {
		if ( !(o instanceof CreaStructure) )
			return false;
		CreaStructure os = (CreaStructure) o;
		return (this.getName() == null
				? os.getName() == null
				: this.getName().equals(os.getName()))
				&& (this.getValue() == null
						? os.getValue() == null
						: this.getValue().equals(os.getValue()));
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.mParent);
		hash = 97 * hash + Objects.hashCode(this.mName);
		hash = 97 * hash + Objects.hashCode(this.mValue);
		return hash;
	}

	/*
     * =============================================== GETTERS AND SETTERS ==============================================
	 */
	@Override
	public CreaElement getParent() {
		return mParent;
	}

	@Override
	public CreaStructure setParent( CreaElement parent ) {
		mParent = parent;
		return this;
	}

	@Override
	public String getName() {
		return mName;
	}

	@Override
	public CreaStructure setName( String name ) {
		mName = name;
		return this;
	}

	@Override
	public String getValue() {
		return mValue;
	}

	@Override
	public CreaStructure setValue( String value ) {
		mValue = value;
		return this;
	}
}
