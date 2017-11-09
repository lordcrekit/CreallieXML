package com.github.lordcrekit.JHierarchyXML.document;

import org.junit.Assert;
import org.junit.Test;

public class StandardPropertyTest {

    public StandardPropertyTest() {
        System.out.println(this.getClass().getName());
    }

    @Test
    public void testEquals() {
        System.out.println("test Equals()");
        final StandardDocument doc = new StandardDocument();
        final XMLProperty p1 = doc.initProperty("alpha", "theta");
        final XMLProperty p2 = doc.initProperty("alpha", "theta");

        // Basic check
        Assert.assertEquals(p1, p2);

        // Check name
        p1.setName("beta");
        Assert.assertNotEquals(p1, p2);
        Assert.assertNotEquals(p2, p1);
        p2.setName("beta");
        Assert.assertEquals(p1, p2);

        // Check value
        p1.setValue("gamma");
        Assert.assertNotEquals(p1, p2);
        Assert.assertNotEquals(p2, p1);
        p2.setValue("gamma");
        Assert.assertEquals(p1, p2);
    }
}
