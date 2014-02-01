package com.example.jacoco;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTestCase {
    
    @Test
    public void testCorner1() {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(-2, -2, 3, 3);
        assertTrue(rect1.intersects(rect2));
        assertTrue(rect2.intersects(rect1));
    }

    @Test
    public void testCorner2() {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(3, -2, 4, 4);
        assertTrue(rect1.intersects(rect2));
        assertTrue(rect2.intersects(rect1));
    }

    @Test
    public void testOutside1() {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(5, 5, 4, 4);
        assertFalse(rect1.intersects(rect2));
        assertFalse(rect2.intersects(rect1));
    }

    @Test
    public void testOutside2() {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(3, -2, 3, 2);
        assertFalse(rect1.intersects(rect2));
        assertFalse(rect2.intersects(rect1));
    }

    @Test
    public void testInside1() {
        Rectangle rect1 = new Rectangle(0, 0, 5, 5);
        Rectangle rect2 = new Rectangle(0, 2, 2, 2);
        assertTrue(rect1.intersects(rect2));
        assertTrue(rect2.intersects(rect1));
    }

}
