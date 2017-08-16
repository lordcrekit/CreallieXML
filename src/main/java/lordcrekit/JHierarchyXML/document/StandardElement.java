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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public final class StandardElement extends StandardStructure implements XMLElement {

    private final List<XMLElement> mChildren = new ArrayList<>();
    private final List<XMLProperty> mPropertys = new ArrayList<>();

    /**
     * Default constructor for StandardElement.
     */
    StandardElement() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof XMLElement))
            return false;
        if (!super.equals(o))
            return false;
        XMLElement oe = (XMLElement) o;
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

    @Override
    public XMLElement setParent(XMLElement element) {
        XMLElement oldParent = getParent();
        super.setParent(element);

        if (oldParent != null && oldParent != getParent())
            oldParent.removeChild(this);

        return this;
    }

    @Override
    public XMLElement getChild(XMLDocumentFilter... filters) {
        return mChildren.stream()
                .filter(i -> Arrays.stream(filters).allMatch(z -> z.accepts(i)))
                .findFirst()
                .orElse(NullElement.getInstance());
    }

    @Override
    public List<XMLElement> getChildren(XMLDocumentFilter... filters) {
        return mChildren.stream()
                .filter(i -> Arrays.stream(filters).allMatch(z -> z.accepts(i)))
                .collect(Collectors.toList());
    }

    @Override
    public XMLElement addChild(XMLElement element) {
        if (element.getParent() != this)
            mChildren.add(element.setParent(this));
        return this;
    }

    @Override
    public XMLElement removeChild(XMLElement element) {
        if (element.getParent() == this)
            mChildren.remove(element.setParent(null));
        return this;
    }

    @Override
    public XMLProperty getProperty(XMLDocumentFilter... filters) {
        return mPropertys.stream().filter(i -> Arrays.stream(filters).allMatch(z -> z.accepts(i)))
                .findFirst()
                .orElse(NullProperty.getInstance());
    }

    @Override
    public List<XMLProperty> getProperties(XMLDocumentFilter... filters) {
        return mPropertys.stream().filter(i -> Arrays.stream(filters).allMatch(z -> z.accepts(i)))
                .collect(Collectors.toList());
    }

    @Override
    public XMLElement addProperty(XMLProperty property) {
        mPropertys.add(property.setParent(this));
        return this;
    }

    @Override
    public XMLElement removeProperty(XMLProperty property) {
        mPropertys.remove(property.setParent(null));
        return this;
    }

    @Override
    public XMLElement setName(String name) {
        super.setName(name);
        return this;
    }

    @Override
    public XMLElement setValue(String value) {
        super.setValue(value);
        return this;
    }

    @Override
    public String toString() {
        return toString(new StringBuilder(), 0).toString();
    }

    @Override
    public StringBuilder toString(StringBuilder strb, int indents) {
        if (indents > 0) {
            char[] indent = new char[indents];
            for (int i = 0; i < indents; i++)
                indent[i] = '\t';
            strb.append(indent);
        }
        strb.append("<").append(this.getName());
        for (XMLProperty i : mPropertys)
            strb.append(" ").append(i.toString());
        strb.append(">");
        if (getValue() != null)
            strb.append(this.getValue()
                    .replaceAll("\n", "\\\\n")
                    .replaceAll("\t", "\\\\t")
                    .replaceAll("\\\\", "\\\\"));
        for (XMLElement i : mChildren) {
            strb.append('\n');
            i.toString(strb, indents + 1);
        }

        return strb;
    }
}
