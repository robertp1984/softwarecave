package com.example.runnablejar;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.codec.digest.DigestUtils;

public class Main {
    
    public static void main(String[] args) throws IOException  {
        if (args.length == 0) {
            System.err.println("Usage: cryptohash file1 ...");
            return;
        }
        
        for (String arg : args) {
            Path file = Paths.get(arg);
            calculateSha256CommonsIO2(file);
        }
    }
        
    private static void calculateSha256CommonsIO2(Path path) throws IOException {
        try (InputStream is = Files.newInputStream(path)) {
            String hashString = DigestUtils.sha256Hex(is);
            System.out.printf("SHA256(%s) = %s%n", path, hashString);
        }
    }

}
