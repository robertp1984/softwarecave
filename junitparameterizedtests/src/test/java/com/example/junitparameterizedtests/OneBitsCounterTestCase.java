package com.example.junitparameterizedtests;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class OneBitsCounterTestCase {

    @Parameters(name = "{index}: test({0}) expected={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { 0b0, 0},
            { 0b001, 1},
            { 0b11011, 4},
            { 0b111111111111111111111111111, 27},
            { 0b0111010111111111111111111111010101111111L, 34}
        });
    }
    
    private long value;
    private int oneBitsCount;
    
    public OneBitsCounterTestCase(long value, int oneBitsCount) {
        this.value = value;
        this.oneBitsCount = oneBitsCount;
    }
    
    @Test
    public void testGetCount() {
        OneBitsCounter counter = new OneBitsCounter();
        assertEquals(counter.getCount(value), oneBitsCount);
    }
    
}
