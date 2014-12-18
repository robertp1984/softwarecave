package org.softwarecave.readfilebylines;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        for (String arg : args) {
            filesReadAllLines(arg);
            bufferedReader(arg);
            bufferedReader2(arg);
            filesLines(arg);
        }
    }

    private static void filesReadAllLines(String fileName)
            throws IOException {
        System.out.print("\nfilesReadLines:\n");
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    private static void bufferedReader(String fileName)
            throws FileNotFoundException, IOException {
        System.out.print("\nbufferedReader:\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        }
    }

    private static void bufferedReader2(String fileName)
            throws IOException {
        System.out.print("\nbufferedReader2:\n");
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        }
    }

    private static void filesLines(String fileName)
            throws IOException {
        System.out.print("\nfilesLines:\n");
        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.forEachOrdered(System.out::println);
        }
    }

}
