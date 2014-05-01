package com.example.customannotation;

import static com.example.customannotation.MyAsserts.*;

public class SimpleTestCase {

    @MyTest(name = "test1WithCustomName", state = MyTestState.ACTIVE)
    public void test1() {
        assertEquals(2, 1 + 1);
        assertEquals(Integer.parseInt("-3"), -3);
    }
    
    @MyTest(expected = NumberFormatException.class)
    public void test2() {
        Integer.parseInt("1.23ddd");
    }
    
    @MyTest(state = MyTestState.INACTIVE)
    public void test3() {
        throw new IllegalStateException("Test case is inactive");
    }
}
