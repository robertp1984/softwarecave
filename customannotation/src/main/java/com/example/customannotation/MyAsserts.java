package com.example.customannotation;

public class MyAsserts {
    public static void assertEquals(int a, int b) {
        if (a != b)
            throw new AssertionError("Failed assertion: " + a + " == " + b);
    }
}
