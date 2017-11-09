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

/**
 * @author William A. Norman (LordCrekit@gmail.com, normanwi@msu.edu)
 */
public interface XMLStructure {

    /**
     * Gets if this XMLStructure exists. The JHierarchyXML will never give a null XMLStructure (including XMLElements
     * and XMLProperties), but will instead give empty implementations.
     *
     * @return <code>true</code> if this XMLStructure exists (and is not an empty implementation), otherwise
     * <code>false</code>.
     */
    boolean exists();

    /**
     * Gets the parent Element of this XMLStructure.
     *
     * @return The parent XMLElement of this XMLStructure.
     */
    XMLElement getParent();

    /**
     * @param element Set the parent XMLElement for this XMLStructure.
     * @return A pointer back to this XMLStructure.
     */
    XMLStructure setParent(XMLElement element);

    /**
     * Get the name of this XMLStructure. Its identifier.
     *
     * @return The name of this XMLStructure.
     */
    String getName();

    /**
     * Set the name for this XMLStructure. Its identifier. Must be a legal XML name.
     *
     * @param name
     *         The name to give this XMLStructure.
     * @return A pointer back to this XMLStructure.
     */
    XMLStructure setName(String name);

    /**
     * Get the String value stored by this XMLStructure. Comments and Elements are excluded if it is an XMLElement.
     * JHierarchyXML only stores hierarchical information, not mixed text and tags.
     *
     * @return The String value of this XMLStructure.
     */
    String getValue();

    /**
     * Set the value for this XMLStructure. If the text contains HTML, it will be excaped when written.
     *
     * @param value
     *         The new value for this XMLStructure.
     * @return A pointer back to this XMLStructure.
     */
    XMLStructure setValue(String value);
}
