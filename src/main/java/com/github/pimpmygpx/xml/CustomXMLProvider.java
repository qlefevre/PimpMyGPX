package com.github.pimpmygpx.xml;

import io.jenetics.jpx.XMLProvider;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;

public class CustomXMLProvider extends XMLProvider {

    private DocumentBuilderFactory documentBuilderFactory = null;


    /**
     * Returns {@link XMLOutputFactory} to be used for writing files.
     *
     * @return the xml output factory
     */
    public XMLOutputFactory xmlOutputFactory() {
        return new CustomXMLOutputFactory(XMLOutputFactory.newInstance());
    }

    @Override
    public DocumentBuilderFactory documentBuilderFactory() {
        // Performance
        if(documentBuilderFactory == null){
            documentBuilderFactory = super.documentBuilderFactory();
        }
        return documentBuilderFactory;
    }

}
