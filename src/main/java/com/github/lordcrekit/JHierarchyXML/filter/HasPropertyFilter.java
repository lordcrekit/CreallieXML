/*
 * The MIT License
 *
 * Copyright 2015 William A. Norman.
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
package com.github.lordcrekit.JHierarchyXML.filter;

import com.github.lordcrekit.JHierarchyXML.document.XMLElement;
import com.github.lordcrekit.JHierarchyXML.document.XMLProperty;

import java.util.function.Predicate;

/**
 * The HasPropertyFilter ensures the number of Properties matching a set of filters adheres to a certain formula.
 *
 * @author william
 */
public final class HasPropertyFilter implements XMLDocumentFilter {

    private final Predicate<Integer> mMatchCount;
    private final XMLDocumentFilter[] mFilters;

    /**
     * Constructs a new HasPropertyFilter where any Element with at least one matching property will be accepted.
     *
     * @param filters
     *         Filters that will be run on all properties.
     */
    public HasPropertyFilter(XMLDocumentFilter... filters) {
        mMatchCount = i -> (i > 0);
        mFilters = filters;
    }

    /**
     * Constructs a new HasPropertyFilter where any numerical check may be defined. Only XMLElements where the number of
     * properties that pass all filters is accepted by the numerical check will be accepted.
     *
     * @param matchCount
     *         A numeral check.
     * @param filters
     *         Filters that will be run on all properties.
     */
    public HasPropertyFilter(Predicate<Integer> matchCount, XMLDocumentFilter... filters) {
        mMatchCount = matchCount;
        mFilters = filters;
    }

    @Override
    public boolean accepts(XMLElement element) {
        return mMatchCount.test(element.getProperties(mFilters).size());
    }

    @Override
    public boolean accepts(XMLProperty property) {
        return false;
    }
}
