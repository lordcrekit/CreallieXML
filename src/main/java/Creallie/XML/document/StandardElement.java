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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class StandardElement extends StandardStructure implements CreaElement {

    /*
     * ================================================ MEMBER VARIABLES ================================================
     */
    private final List<CreaElement> mChildren = new ArrayList<>();
    private final List<CreaProperty> mPropertys = new ArrayList<>();

    /*
     * ================================================== CONSTRUCTORS ==================================================
     */
    /**
     * Default constructor for StandardElement.
     */
    StandardElement() {
        super();
    }

	/*
     * ================================================ PRIMARY FUNCTIONS ===============================================
	 */
	@Override
	public boolean equals(Object o) {
		if ( !(o instanceof CreaElement) )
			return false;
		if ( !super.equals(o))
			return false;
		CreaElement oe = (CreaElement) o;
		return this.mChildren.equals(oe.getChildren())
				&& this.mPropertys.equals(oe.getProperties());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + Objects.hashCode(this.mChildren);
		hash = 41 * hash + Objects.hashCode(this.mPropertys);
		return hash;
	}
	
    /*
     * =============================================== GETTERS AND SETTERS ==============================================
     */
    @Override
    public CreaElement setParent( CreaElement element ) {
        CreaElement oldParent = getParent();
        super.setParent(element);

        if ( oldParent != null && oldParent != getParent() )
            oldParent.removeChild(this);

        return this;
    }

    @Override
    public CreaElement getChild( CreaDocumentFilter... filters ) {
        return mChildren.stream().filter(i -> Arrays.stream(filters).allMatch(z -> z.accepts(i))).findFirst().orElse(NullElement.getInstance());
    }

    @Override
    public List<CreaElement> getChildren( CreaDocumentFilter... filters ) {
        return mChildren.stream().filter(i -> Arrays.stream(filters).allMatch(z -> z.accepts(i))).collect(Collectors.toList());
    }

    @Override
    public CreaElement addChild( CreaElement element ) {
        if ( element.getParent() != this )
            mChildren.add(element.setParent(this));
        return this;
    }

    @Override
    public CreaElement removeChild( CreaElement element ) {
        if ( element.getParent() == this )
            mChildren.remove(element.setParent(null));
        return this;
    }

    @Override
    public CreaProperty getProperty( CreaDocumentFilter... filters ) {
        return mPropertys.stream().filter(i -> Arrays.stream(filters).allMatch(z -> z.accepts(i))).findFirst().orElse(NullProperty.getInstance());
    }

    @Override
    public List<CreaProperty> getProperties( CreaDocumentFilter... filters ) {
        return mPropertys.stream().filter(i -> Arrays.stream(filters).allMatch(z -> z.accepts(i))).collect(Collectors.toList());
    }

    @Override
    public CreaElement addProperty( CreaProperty property ) {
        mPropertys.add(property.setParent(this));
        return this;
    }

    @Override
    public CreaElement removeProperty( CreaProperty property ) {
        mPropertys.remove(property.setParent(null));
        return this;
    }

    @Override
    public CreaElement setName( String name ) {
        super.setName(name);
        return this;
    }

    @Override
    public CreaElement setValue( String value ) {
        super.setValue(value);
        return this;
    }

    /*
     * ================================================ VISUAL FUNCTIONS ================================================
     */
    @Override
    public String toString() {
        return toString(new StringBuilder(), 0).toString();
    }

    @Override
    public StringBuilder toString( StringBuilder strb, int indents ) {
        if ( indents > 0 ) {
            char[] indent = new char[indents];
            for ( int i = 0; i < indents; i++ )
                indent[i] = '\t';
            strb.append(indent);
        }
        strb.append("<").append(this.getName());
        for ( CreaProperty i : mPropertys )
            strb.append(" ").append(i.toString());
        strb.append(">");
        if ( getValue() != null )
            strb.append(this.getValue()
					.replaceAll("\n", "\\\\n")
					.replaceAll("\t", "\\\\t")
					.replaceAll("\\\\", "\\\\"));
        for ( CreaElement i : mChildren ){
            strb.append('\n');
			i.toString(strb, indents + 1);
		}

        return strb;
    }
}
