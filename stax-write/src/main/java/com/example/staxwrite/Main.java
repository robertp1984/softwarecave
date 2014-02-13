package com.example.staxwrite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.stream.XMLStreamException;

public class Main {

    public static void main(String[] args) throws IOException, XMLStreamException {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Mark Twain", "The Adventures of Tom Sawyer", Category.FICTION, "English", 1876));
        books.add(new Book("Niklaus Wirth", "The Programming Language Pascal", Category.PASCAL, "English", 1971));
        books.add(new Book(Arrays.asList("O.-J. Dahl", "E. W. Dijkstra", "C. A. R. Hoare"),
                "The Programming Language Pascal", Category.PROGRAMMING, "English", 1972));

        NoNSWriter writer1 = new NoNSWriter();
        Path outfile1 = Files.createTempFile("stax-write-nons", null);
        writer1.writeToXml(outfile1, books);
        
        NSWriter writer2 = new NSWriter();
        Path outfile2 = Files.createTempFile("stax-write-ns", null);
        writer2.writeToXml(outfile2, books);

        DefaultNSWriter writer3 = new DefaultNSWriter();
        Path outfile3 = Files.createTempFile("stax-write-default-ns", null);
        writer3.writeToXml(outfile3, books);
    }

}
