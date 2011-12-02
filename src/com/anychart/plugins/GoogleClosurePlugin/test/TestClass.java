package com.anychart.plugins.GoogleClosurePlugin.test;

import junit.framework.TestCase;
import org.junit.*;
/**
 * Created by IntelliJ IDEA.
 * User: buka
 * Date: 03.12.11
 * Time: 2:30
 * To change this template use File | Settings | File Templates.
 */
public class TestClass extends TestCase {

    public TestClass(String name) {
        super(name);

    }

    @Before
    protected void setUp() {

        System.out.println("Set Up Method!");
    }

    @After
    protected void tearDown() {
        System.out.println("Tear Down Method!");
    }

    @Test
    public void testOne() {

        System.out.println("#test1");
        assertTrue(true);
    }

    @Test
    public void testTwo() {
        System.out.println("#test2");
        assertTrue(true);
    }
}