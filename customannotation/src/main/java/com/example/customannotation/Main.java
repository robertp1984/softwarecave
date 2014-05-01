package com.example.customannotation;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        MyTestRunner runner = new MyTestRunner();
        runner.run(SimpleTestCase.class);
    }

}
