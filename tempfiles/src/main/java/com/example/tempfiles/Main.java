package com.example.tempfiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        main.createTempFileOldWay();
        main.createTempFileWithDirOldWay();
        main.createTempFile();
        main.createTempFileWithDir();
        main.createTempFileShutdownHook();
        main.createTempFileDeleteOnExit();
        main.createTempFileDeleteOnClose();
    }

    private void createTempFileOldWay() throws IOException {
        File tempFile = File.createTempFile("tempfile-old", ".tmp");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(tempFile));
            writer.println("Line1");
            writer.println("Line2");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
    }

    private void createTempFileWithDirOldWay() throws IOException {
        File tempDir = new File(System.getProperty("java.io.tmpdir", null), "tempdir-old");
        if (!tempDir.exists() && !tempDir.mkdir()) {
            throw new IIOException("Failed to create temporary directory " + tempDir);
        }

        File tempFile = File.createTempFile("tempfile-old", ".tmp", tempDir);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(tempFile));
            writer.println("Line1");
            writer.println("Line2");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
    }

    private void createTempFile() throws IOException {
        Path tempFile = Files.createTempFile("tempfiles", ".tmp");
        List<String> lines = Arrays.asList("Line1", "Line2");
        Files.write(tempFile, lines, Charset.defaultCharset(), StandardOpenOption.WRITE);

        System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
    }

    private void createTempFileWithDir() throws IOException {
        Path tempDir = Files.createTempDirectory("tempfiles");

        Path tempFile = Files.createTempFile(tempDir, "tempfiles", ".tmp");
        List<String> lines = Arrays.asList("Line1", "Line2");
        Files.write(tempFile, lines, Charset.defaultCharset(), StandardOpenOption.WRITE);

        System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
    }

    private void createTempFileShutdownHook() throws IOException {
        final Path tempFile = Files.createTempFile("tempfiles-shutdown-hook", ".tmp");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    Files.delete(tempFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        List<String> lines = Arrays.asList("Line1", "Line2");
        Files.write(tempFile, lines, Charset.defaultCharset(), StandardOpenOption.WRITE);

        System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
    }

    private void createTempFileDeleteOnExit() throws IOException {
        Path tempFile = Files.createTempFile("tempfiles-delete-on-exit", ".tmp");
        tempFile.toFile().deleteOnExit();
        List<String> lines = Arrays.asList("Line1", "Line2");
        Files.write(tempFile, lines, Charset.defaultCharset(), StandardOpenOption.WRITE);

        System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
    }
    
    private void createTempFileDeleteOnClose() throws IOException {
        Path tempFile = Files.createTempFile("tempfiles-delete-on-close", ".tmp");
        List<String> lines = Arrays.asList("Line1", "Line2");
        Files.write(tempFile, lines, Charset.defaultCharset(), StandardOpenOption.WRITE, StandardOpenOption.DELETE_ON_CLOSE);

        System.out.printf("Wrote text to temporary file %s%n", tempFile.toString());
    }
}
