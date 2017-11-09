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
package lordcrekit.JHierarchyXML.filter;

import lordcrekit.JHierarchyXML.document.XMLElement;
import lordcrekit.JHierarchyXML.document.XMLProperty;

import java.util.function.Predicate;

/**
 * The HasElementFilter ensures the number of XMLElement children of an XMLElement adheres to a given formula.
 *
 * @author William A. Norman
 */
public class HasElementFilter implements XMLDocumentFilter {

    private final Predicate<Integer> mMatchCount;
    private final XMLDocumentFilter[] mFilters;

    /**
     * Construct a new HasElementFilter out of the given XMLDocumentFilters. It checks that at least one XMLElement
     * child matches its filters.
     *
     * @param filters
     *         The filters to sort with.
     */
    public HasElementFilter(XMLDocumentFilter... filters) {
        mMatchCount = i -> (i > 0);
        mFilters = filters;
    }

    /**
     * Construct a new HasElementFilter out of the given XMLDocumentFilters and a Predicate. The predicates test method
     * is used to check if the number of matching children is valid.
     *
     * @param matchCount
     *         The Predicate to test with.
     * @param filters
     *         The filters to sort with.
     */
    public HasElementFilter(Predicate<Integer> matchCount, XMLDocumentFilter... filters) {
        mMatchCount = matchCount;
        mFilters = filters;
    }

    @Override
    public boolean accepts(XMLElement element) {
        return mMatchCount.test(element.getChildren(mFilters).size());
    }

    @Override
    public boolean accepts(XMLProperty property) {
        return false;
    }
}
