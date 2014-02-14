package com.example.staxwrite;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class NoNSWriter {

    public void writeToXml(Path path, List<Book> books) throws IOException, XMLStreamException {
        try (OutputStream os = Files.newOutputStream(path)) {
            XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = null;
            try {
                writer = outputFactory.createXMLStreamWriter(os, "utf-8");
                writeBooksElem(writer, books);
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
    }

    private void writeBooksElem(XMLStreamWriter writer, List<Book> books) throws XMLStreamException {
        writer.writeStartDocument("utf-8", "1.0");
        writer.writeComment("Describes list of books");
        
        writer.writeStartElement("books");
        for (Book book : books)
            writeBookElem(writer, book);
        writer.writeEndElement();

        writer.writeEndDocument();
    }

    private void writeBookElem(XMLStreamWriter writer, Book book) throws XMLStreamException {
        writer.writeStartElement("book");
        writer.writeAttribute("language", book.getLanguage());

        writeAuthorsElem(writer, book.getAuthors());

        writer.writeStartElement("title");
        writer.writeCData(book.getTitle());
        writer.writeEndElement();

        writer.writeStartElement("category");
        writer.writeCharacters(book.getCategory().name());
        writer.writeEndElement();

        writer.writeStartElement("year");
        writer.writeCharacters(Integer.toString(book.getYear()));
        writer.writeEndElement();

        writer.writeEndElement();
    }

    private void writeAuthorsElem(XMLStreamWriter writer, List<String> authors) throws XMLStreamException {
        writer.writeStartElement("authors");
        for (String author : authors) {
            writer.writeStartElement("author");
            writer.writeCharacters(author);
            writer.writeEndElement();
        }
        writer.writeEndElement();
    }
}
