package com.example.filevisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        for (String arg : args) {
            Path path = Paths.get(arg);
            visitRecursively(path);
        }
    }

    private static void visitRecursively(Path path) throws IOException {
        PrintingFileVisitor visitor = new PrintingFileVisitor();
        Files.walkFileTree(path, visitor);
        visitor.printSummary();
    }
}
