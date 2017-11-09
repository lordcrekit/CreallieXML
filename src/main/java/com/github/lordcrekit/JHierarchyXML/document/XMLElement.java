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
package com.github.lordcrekit.JHierarchyXML.document;

import com.github.lordcrekit.JHierarchyXML.filter.XMLDocumentFilter;

import java.util.List;

/**
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public interface XMLElement extends XMLStructure {

    @Override
    XMLElement setParent(XMLElement element);

    @Override
    XMLElement setName(String name);

    @Override
    XMLElement setValue(String value);

    // <editor-fold defaultstate="collapsed" desc="Children"

    /**
     * Get the first XMLElement child of this XMLElement that matches the given filters.
     *
     * @param filters
     *         The filters to match with.
     * @return The first matching XMLElement child of this XMLElement, or {@link NullElement} if there are no matches.
     */
    XMLElement getChild(XMLDocumentFilter... filters);

    /**
     * Get all XMLElement children of this XMLElement that match the given filters.
     *
     * @param filters
     *         The filters to match with.
     * @return All matching XMLElement children of this XMLElement.
     */
    List<XMLElement> getChildren(XMLDocumentFilter... filters);

    /**
     * Add a new XMLElement child to this XMLElement. Children are written to file AFTER text content.
     *
     * @param element
     *         The XMLElement to add to this XMLElement.
     * @return A pointer back to this XMLElement.
     */
    XMLElement addChild(XMLElement element);

    /**
     * Remove the given XMLElement from this XMLElement's children.
     *
     * @param element
     *         The XMLElement child to remove.
     * @return A pointer back to this XMLElement.
     */
    XMLElement removeChild(XMLElement element);

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Properties">

    /**
     * Get the first XMLProperty in this XMLElement that matches the given filters.
     *
     * @param filters
     *         The filters to match with.
     * @return The first matching XMLProperty, or {@link NullProperty} if there is no match.
     */
    XMLProperty getProperty(XMLDocumentFilter... filters);

    /**
     * Get all XMLProperties that match the given filters.
     *
     * @param filters
     *         The filters to match with.
     * @return All matching XMLProperties.
     */
    List<XMLProperty> getProperties(XMLDocumentFilter... filters);

    /**
     * Add a new XMLProperty to this XMLElement.
     *
     * @param property
     *         The XMLProperty to add.
     * @return A pointer back to this XMLElement.
     */
    XMLElement addProperty(XMLProperty property);

    /**
     * Remove the given XMLProperty from this XMLElement.
     *
     * @param property
     *         The XMLProperty to remove.
     * @return A pointer back to this XMLElement.
     */
    XMLElement removeProperty(XMLProperty property);

    // </editor-fold>

    /**
     * Add a new comment to this XMLElement. There is no way to retrieve comments. Comments are written to file in the
     * order provided.Comments are not read from files,only written. Comments to not influence the equality of
     * XMLElements.
     *
     * @param comment
     *         The comment to be added to this XMLElement.
     * @return A pointer back to this XMLElement.
     */
    XMLElement addComment(String comment);

    /**
     * Get all comments currently in this XMLElement.
     *
     * @return All comments currently in this XMLElement.
     */
    List<String> getComments();

    /**
     * Append this XMLElement as valid XML to a StringBuilder. This method is applied recursively.
     *
     * @param strb
     *         The StringBuilder being appended to.
     * @param indents
     *         The indent level to start at. If negative, no indent will be applied.
     * @return A pointer back to the StringBuilder.
     */
    StringBuilder toString(StringBuilder strb, int indents);
}
