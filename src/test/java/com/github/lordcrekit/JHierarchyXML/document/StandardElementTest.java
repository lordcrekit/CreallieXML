package com.github.lordcrekit.JHierarchyXML.document;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author William A. Norman
 */
public class StandardElementTest {

    public StandardElementTest() {
        System.out.println(this.getClass().getName());
    }

    @Test
    public void testEquals() {
        System.out.println("test Equals()");
        final StandardDocument doc = new StandardDocument();
        final XMLElement o1 = doc.initElement("alpha");
        final XMLElement o2 = doc.initElement("alpha");

        // Initial, empty check.
        Assert.assertEquals(o1, o2);

        // Check name equality.
        o1.setName("beta");
        Assert.assertNotEquals(o1, o2);
        Assert.assertNotEquals(o2, o1);
        o2.setName("beta");
        Assert.assertEquals(o1, o2);

        // Check value equality.
        o1.setValue("theta");
        Assert.assertNotEquals(o1, o2);
        Assert.assertNotEquals(o2, o1);
        o2.setValue("gamma");
        Assert.assertNotEquals(o1, o2);
        Assert.assertNotEquals(o2, o1);
        o2.setValue("theta");
        Assert.assertEquals(o1, o2);

        // Check property equality
        final XMLProperty p1 = doc.initProperty("prop-alpha", "theta");
        final XMLProperty p2 = doc.initProperty("prop-alpha", "gamma");
        o1.addProperty(p1);
        Assert.assertNotEquals(o1, o2);
        Assert.assertNotEquals(o2, o1);
        o2.addProperty(p2);
        Assert.assertNotEquals(o1, o2);
        Assert.assertNotEquals(o2, o1);
        p2.setValue("theta");
        Assert.assertEquals(o1, o2);

        // Check child equality
        final XMLElement c1 = doc.initElement("child-alpha");
        final XMLElement c2 = doc.initElement("child-beta");
        o1.addChild(c1);
        Assert.assertNotEquals(o1, o2);
        Assert.assertNotEquals(o2, o1);
        o2.addChild(c2);
        Assert.assertNotEquals(o1, o2);
        Assert.assertNotEquals(o2, o1);
        c2.setName("child-alpha");
        Assert.assertEquals(o1, o2);
    }
}
