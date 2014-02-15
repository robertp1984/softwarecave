package com.example.staxread;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class XHTMLExtractor {

    private static final String XHTML_NS = "http://www.w3.org/1999/xhtml";
    private List<String> images;
    private List<String> links;

    public XHTMLExtractor() {
        images = new ArrayList<>();
        links = new ArrayList<>();
    }

    public List<String> getLinks() {
        return links;
    }

    public List<String> getImages() {
        return images;
    }

    public void readFromXML(InputStream is) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            reader = inputFactory.createXMLStreamReader(is);
            extractImagesAndLinks(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private void extractImagesAndLinks(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();
            if (eventType == XMLStreamReader.START_ELEMENT) {
                handleStartElement(reader);
            }
        }
    }

    private void handleStartElement(XMLStreamReader reader) {
        if (XHTML_NS.equals(reader.getNamespaceURI())) {
            String localName = reader.getLocalName();
            if (localName.equalsIgnoreCase("a")) {
                handleLinkAttributes(reader);
            } else if (localName.equalsIgnoreCase("img")) {
                handleImageAttributes(reader);
            }
        }
    }

    private void handleLinkAttributes(XMLStreamReader reader) {
        String value = reader.getAttributeValue(null, "href");
        if (value != null) {
            links.add(value);
        }
    }

    private void handleImageAttributes(XMLStreamReader reader) {
        String value = reader.getAttributeValue(null, "src");
        if (value != null) {
            images.add(value);
        }
    }
}
