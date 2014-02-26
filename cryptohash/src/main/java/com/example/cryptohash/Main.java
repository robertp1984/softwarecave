package com.example.cryptohash;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import org.apache.commons.codec.digest.DigestUtils;

public class Main {
    
    private static final int BUFFER_SIZE = 1024;
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length == 0) {
            System.err.println("Usage: cryptohash file1 ...");
            return;
        }
        
        for (int i = 0; i < args.length; i++) {
            Path file = Paths.get(args[i]);
            calculateSha256(file);
            calculateSha256CommonsIO(file);
            calculateSha256CommonsIO2(file);
        }
    }
    
    private static String bytesToHex(byte[] hashValue) {
        Formatter form = new Formatter();
        for (int i = 0; i < hashValue.length; i++)
            form.format("%02x", hashValue[i]);
        return form.toString();
    }
    
    private static void calculateSha256(Path path) throws IOException, NoSuchAlgorithmException {
        byte[] buffer = new byte[BUFFER_SIZE];
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        
        try (InputStream is = Files.newInputStream(path)) {
            while (true) {
                int readBytes = is.read(buffer);
                if (readBytes > 0)
                    digest.update(buffer, 0, readBytes);
                else
                    break;
            }
        }
        
        byte[] hashValue = digest.digest();
        System.out.printf("SHA256(%s) = %s%n", path, bytesToHex(hashValue));
    }

    private static void calculateSha256CommonsIO(Path path) throws IOException {
        try (InputStream is = Files.newInputStream(path)) {
            byte[] hashValue = DigestUtils.sha256(is);
            System.out.printf("SHA256(%s) = %s%n", path, bytesToHex(hashValue));
        }
    }
    
    private static void calculateSha256CommonsIO2(Path path) throws IOException {
        try (InputStream is = Files.newInputStream(path)) {
            String hashString = DigestUtils.sha256Hex(is);
            System.out.printf("SHA256(%s) = %s%n", path, hashString);
        }
    }

}
