package com.example.serialversionuid;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 2) {
            System.err.println("Usage: serialversionuid read|write FILE");
            return;
        }
        final String operation = args[0];
        final String file = args[1];
        
        
        if (operation.equals("read")) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
                Person person = (Person) is.readObject();
                System.out.println("Read person: " + person);
            }
        } else if (operation.equals("write")) {
            Person person = new Person("James", "Bond");
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
                os.writeObject(person);
            }
        }
    }
    
}
