package com.github.pimpmygpx.xml;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class CustomXMLStreamWriter implements XMLStreamWriter {

    private final XMLStreamWriter delegate;

    public CustomXMLStreamWriter(XMLStreamWriter delegate) {
        this.delegate = delegate;
    }

    public void writeStartElement(String startElement) throws XMLStreamException {
        delegate.writeStartElement(startElement);
        if("gpx".equals(startElement)){
            delegate.writeNamespace("gpxtpx","http://www.garmin.com/xmlschemas/TrackPointExtension/v1");
        }
    }

    public void writeStartElement(String s, String s1) throws XMLStreamException {
        delegate.writeStartElement(s, s1);
    }

    public void writeStartElement(String s, String s1, String s2) throws XMLStreamException {
        delegate.writeStartElement(s, s1, s2);
    }

    public void writeEmptyElement(String s, String s1) throws XMLStreamException {
        delegate.writeEmptyElement(s, s1);
    }

    public void writeEmptyElement(String s, String s1, String s2) throws XMLStreamException {
        delegate.writeEmptyElement(s, s1, s2);
    }

    public void writeEmptyElement(String s) throws XMLStreamException {
        delegate.writeEmptyElement(s);
    }

    public void writeEndElement() throws XMLStreamException {
        delegate.writeEndElement();
    }

    public void writeEndDocument() throws XMLStreamException {
        delegate.writeEndDocument();
    }

    public void close() throws XMLStreamException {
        delegate.close();
    }

    public void flush() throws XMLStreamException {
        delegate.flush();
    }

    public void writeAttribute(String s, String s1) throws XMLStreamException {
        delegate.writeAttribute(s, s1);
    }

    public void writeAttribute(String s, String s1, String s2, String s3) throws XMLStreamException {
        delegate.writeAttribute(s, s1, s2, s3);
    }

    public void writeAttribute(String s, String s1, String s2) throws XMLStreamException {
        delegate.writeAttribute(s, s1, s2);
    }

    public void writeNamespace(String prefix, String namespaceURI) throws XMLStreamException {
        switch(prefix){
            case "gpxtpx":
            case "xmlns":
                return;
        }
        delegate.writeNamespace(prefix, namespaceURI);
    }

    public void writeDefaultNamespace(String s) throws XMLStreamException {
        delegate.writeDefaultNamespace(s);
    }

    public void writeComment(String s) throws XMLStreamException {
        delegate.writeComment(s);
    }

    public void writeProcessingInstruction(String s) throws XMLStreamException {
        delegate.writeProcessingInstruction(s);
    }

    public void writeProcessingInstruction(String s, String s1) throws XMLStreamException {
        delegate.writeProcessingInstruction(s, s1);
    }

    public void writeCData(String s) throws XMLStreamException {
        delegate.writeCData(s);
    }

    public void writeDTD(String s) throws XMLStreamException {
        delegate.writeDTD(s);
    }

    public void writeEntityRef(String s) throws XMLStreamException {
        delegate.writeEntityRef(s);
    }

    public void writeStartDocument() throws XMLStreamException {
        delegate.writeStartDocument();
    }

    public void writeStartDocument(String s) throws XMLStreamException {
        delegate.writeStartDocument(s);
    }

    public void writeStartDocument(String s, String s1) throws XMLStreamException {
        delegate.writeStartDocument(s, s1);
    }

    public void writeCharacters(String s) throws XMLStreamException {
        delegate.writeCharacters(s);
    }

    public void writeCharacters(char[] chars, int i, int i1) throws XMLStreamException {
        delegate.writeCharacters(chars, i, i1);
    }

    public String getPrefix(String s) throws XMLStreamException {
        return delegate.getPrefix(s);
    }

    public void setPrefix(String s, String s1) throws XMLStreamException {
        delegate.setPrefix(s, s1);
    }

    public void setDefaultNamespace(String s) throws XMLStreamException {
        delegate.setDefaultNamespace(s);
    }

    public void setNamespaceContext(NamespaceContext namespaceContext) throws XMLStreamException {
        delegate.setNamespaceContext(namespaceContext);
    }

    public NamespaceContext getNamespaceContext() {
        return delegate.getNamespaceContext();
    }

    public Object getProperty(String s) throws IllegalArgumentException {
        return delegate.getProperty(s);
    }
}
