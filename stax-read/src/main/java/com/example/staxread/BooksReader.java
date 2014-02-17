package com.example.staxread;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class BooksReader {

    public List<Book> readFromXML(InputStream is) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            reader = inputFactory.createXMLStreamReader(is);
            return readDocument(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private List<Book> readDocument(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("books"))
                        return readBooks(reader);
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        throw new XMLStreamException("Premature end of file");
    }

    private List<Book> readBooks(XMLStreamReader reader) throws XMLStreamException {
        List<Book> books = new ArrayList<>();
        
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("book"))
                        books.add(readBook(reader));
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return books;
            }
        }
        throw new XMLStreamException("Premature end of file");
    }

    private Book readBook(XMLStreamReader reader) throws XMLStreamException {
        Book book = new Book("", "", null, "", 0);
        book.setLanguage(reader.getAttributeValue(null, "language"));
        
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("authors"))
                        book.setAuthors(readAuthors(reader));
                    else if (elementName.equals("title"))
                        book.setTitle(readCharacters(reader));
                    else if (elementName.equals("category"))
                        book.setCategory(readCategory(reader));
                    else if (elementName.equals("year"))
                        book.setYear(readInt(reader));
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return book;
            }
        }
        throw new XMLStreamException("Premature end of file");
    }

    private List<String> readAuthors(XMLStreamReader reader) throws XMLStreamException {
        List<String> authors = new ArrayList<>();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("author"))
                        authors.add(readCharacters(reader));
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return authors;
            }
        }
        throw new XMLStreamException("Premature end of file");

    }
    
    private String readCharacters(XMLStreamReader reader) throws XMLStreamException {
        StringBuilder result = new StringBuilder();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.CHARACTERS:
                case XMLStreamReader.CDATA:
                    result.append(reader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return result.toString();
            }
        }
        throw new XMLStreamException("Premature end of file");
    }
    
    private Category readCategory(XMLStreamReader reader) throws XMLStreamException {
        String characters = readCharacters(reader);
        try {
            return Category.valueOf(characters);
        } catch(IllegalArgumentException e) {
            throw new XMLStreamException("Invalid category " + characters);
        }
    }
    
    private int readInt(XMLStreamReader reader) throws XMLStreamException {
        String characters = readCharacters(reader);
        try {
            return Integer.valueOf(characters);
        } catch (NumberFormatException e) {
            throw new XMLStreamException("Invalid integer " + characters);
        }
    }
}
