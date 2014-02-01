package com.example.jacoco;

public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Rectangle(int x, int y, int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Dimensions are not positive");
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean intersects(Rectangle other) {
        if (x + width <= other.x)
            return false;
        if (x >= other.x + other.width)
            return false;
        return (y + height > other.y && y < other.y + other.height);
    }
}
