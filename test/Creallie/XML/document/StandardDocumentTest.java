/*
 * The MIT License
 *
 * Copyright 2016 lordc.
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

import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author lordc
 */
public class StandardDocumentTest {
	
	public StandardDocumentTest() {
		System.out.println("StandardDocument");
	}

	@BeforeClass
	public static void setUpClass() {
		System.out.println(StandardDocument.class.getName());
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("\n");
	}

	@Test
	public void testEquals() {
		System.out.println("test Equals");
		StandardDocument o1 = new StandardDocument();
		StandardDocument o2 = new StandardDocument();
		// Test empty
		assertEquals(true, o1.equals(o2));

		// Test children
		o1.setRootElement(o1.initElement("root"));
		assertEquals(false, o1.equals(o2));
		assertEquals(false, o2.equals(o1));
		o2.setRootElement(o2.initElement("root"));
		assertEquals(true, o1.equals(o2));

		// Test nested children		
		CreaElement o1c = o1.getRootElement().addChild(o1.initElement("child"));
		assertEquals(false, o1.equals(o2));
		assertEquals(false, o2.equals(o1));
		CreaElement o2c = o2.getRootElement().addChild(o2.initElement("child"));
		assertEquals(true, o1.equals(o2));

		// Test values
		o1c.setValue("value");
		assertEquals(false, o1.equals(o2));
		assertEquals(false, o2.equals(o1));
		o2c.setValue("value");
		assertEquals(true, o1.equals(o2));

		// Test properties
		o1c.addProperty(o1.initProperty("prop", "prop_value"));
		assertEquals(false, o1.equals(o2));
		assertEquals(false, o2.equals(o1));
		o2c.addProperty(o2.initProperty("prop", "prop_value"));
		assertEquals(true, o1.equals(o2));
	}
}
