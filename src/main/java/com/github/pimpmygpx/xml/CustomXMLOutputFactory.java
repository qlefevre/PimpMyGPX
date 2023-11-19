package com.github.pimpmygpx.xml;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import java.io.OutputStream;
import java.io.Writer;

public class CustomXMLOutputFactory extends XMLOutputFactory {

    private final XMLOutputFactory delegate;

    public CustomXMLOutputFactory(XMLOutputFactory delegate) {
        this.delegate = delegate;
    }

    public XMLStreamWriter createXMLStreamWriter(Writer writer) throws XMLStreamException {
        return new CustomXMLStreamWriter(delegate.createXMLStreamWriter(writer));
    }

    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream) throws XMLStreamException {
        return new CustomXMLStreamWriter(delegate.createXMLStreamWriter(outputStream));
    }

    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream, String s) throws XMLStreamException {
        return new CustomXMLStreamWriter(delegate.createXMLStreamWriter(outputStream, s));
    }

    public XMLStreamWriter createXMLStreamWriter(Result result) throws XMLStreamException {
        return new CustomXMLStreamWriter(delegate.createXMLStreamWriter(result));
    }

    public XMLEventWriter createXMLEventWriter(Result result) throws XMLStreamException {
        return delegate.createXMLEventWriter(result);
    }

    public XMLEventWriter createXMLEventWriter(OutputStream outputStream) throws XMLStreamException {
        return delegate.createXMLEventWriter(outputStream);
    }

    public XMLEventWriter createXMLEventWriter(OutputStream outputStream, String s) throws XMLStreamException {
        return delegate.createXMLEventWriter(outputStream, s);
    }

    public XMLEventWriter createXMLEventWriter(Writer writer) throws XMLStreamException {
        return delegate.createXMLEventWriter(writer);
    }

    public void setProperty(String s, Object o) throws IllegalArgumentException {
        delegate.setProperty(s, o);
    }

    public Object getProperty(String s) throws IllegalArgumentException {
        return delegate.getProperty(s);
    }

    public boolean isPropertySupported(String s) {
        return delegate.isPropertySupported(s);
    }
}
