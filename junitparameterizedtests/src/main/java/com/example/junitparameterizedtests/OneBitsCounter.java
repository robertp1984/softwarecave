package com.example.junitparameterizedtests;

public class OneBitsCounter {

    int getCount(long value) {
        value = value - ((value >> 1) & 0x5555555555555555L);
        value = (value & 0x3333333333333333L)
                + ((value >> 2) & 0x3333333333333333L);
        value = ((value + (value >> 4)) & 0x0F0F0F0F0F0F0F0FL);
        return (int) ((value * (0x0101010101010101L)) >> 56);
    }
}
