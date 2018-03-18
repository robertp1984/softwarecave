package org.softwarecave.deletedirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Random;
import org.apache.commons.io.FileUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        Path tmpRootDir = Paths.get("/tmp/deldir");
        System.out.printf("Directory to remove is %s\n", tmpRootDir);

        createDirStructure(tmpRootDir, 2);
        deleteDirectoryRecursionJava6(tmpRootDir.toFile());
        System.out.printf("deleteDirectoryRecursionJava6 success=%s\n", !Files.exists(tmpRootDir));

        createDirStructure(tmpRootDir, 2);
        deleteDirectoryRecursion(tmpRootDir);
        System.out.printf("deleteDirectoryRecursion success=%s\n", !Files.exists(tmpRootDir));

        createDirStructure(tmpRootDir, 2);
        deleteDirectoryWalkTree(tmpRootDir);
        System.out.printf("deleteDirectoryWalkTree success=%s\n", !Files.exists(tmpRootDir));

        createDirStructure(tmpRootDir, 2);
        deleteDirectoryStream(tmpRootDir);
        System.out.printf("deleteDirectoryStream success=%s\n", !Files.exists(tmpRootDir));
        
        createDirStructure(tmpRootDir, 2);
        deleteDirectoryCommonsIO(tmpRootDir.toFile());
        System.out.printf("deleteDirectoryCommonsIO success=%s\n", !Files.exists(tmpRootDir));
    }

    private static void createDirStructure(Path path, int level) throws IOException {
        Files.createDirectory(path);

        Random random = new Random();
        int count = random.nextInt(10);
        for (int i = 0; i < count; i++) {
            Path subpath = path.resolve("file" + i);
            Files.createFile(subpath);
        }
        for (int i = 0; i < count; i++) {
            Path subpath = path.resolve("dir" + i);
            if (level > 0) {
                createDirStructure(subpath, level - 1);
            }
        }
    }

    private static void deleteDirectoryRecursionJava6(File file) throws IOException {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectoryRecursionJava6(entry);
                }
            }
        }
        if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
        }
    }

    private static void deleteDirectoryRecursion(Path path) throws IOException {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteDirectoryRecursion(entry);
                }
            }
        }
        Files.delete(path);
    }

    public static void deleteDirectoryWalkTree(Path path) throws IOException {
        FileVisitor visitor = new SimpleFileVisitor<Path>() {
            
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(path, visitor);
    }
    
    public static void deleteDirectoryStream(Path path) throws IOException {
        Files.walk(path)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }
    
    public static void deleteDirectoryCommonsIO(File file) throws IOException {
        FileUtils.deleteDirectory(file);
    }

}
