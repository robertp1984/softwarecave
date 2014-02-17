package com.example.staxread;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.stream.XMLStreamException;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException {
        try (InputStream is = Main.class.getResourceAsStream("/sample.xhtml")) {
            if (is == null)
                throw new IOException("Failed to open resource");

            BooksReader reader = new BooksReader();
            System.out.println(reader.readFromXML(is));
        }
    }
}
