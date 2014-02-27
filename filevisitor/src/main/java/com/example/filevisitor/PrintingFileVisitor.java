package com.example.filevisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

class PrintingFileVisitor implements FileVisitor<Path> {

    private static final String TAB = "    ";
    private static final int TAB_SIZE = TAB.length();
    
    private StringBuffer prefix;
    private int fileCount;
    private int directoryCount;
    
    public PrintingFileVisitor() {
        prefix = new StringBuffer();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.printf("%s%s:%n", prefix, dir.getFileName());
        prefix.append(TAB);
        directoryCount++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        prefix.setLength(prefix.length() - TAB_SIZE);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.printf("%s%s%n", prefix, file.getFileName());
        fileCount++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.printf("%s%s (failed)%n", prefix, file.getFileName());
        return FileVisitResult.CONTINUE;
    }

    public void printSummary() {
        System.out.printf("Files: %d    Directories: %d%n", fileCount, directoryCount);
    }
    
}
