package com.example.staxread;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.stream.XMLStreamException;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException {
        try (InputStream is = Main.class.getResourceAsStream("/sample.xhtml")) {
            if (is == null)
                throw new IOException("Failed to open resource");

            XHTMLExtractor reader = new XHTMLExtractor();
            reader.readFromXML(is);
            System.out.printf("Links: %s%n%n", reader.getLinks());
            System.out.printf("Images: %s%n", reader.getImages());
        }
    }
}
