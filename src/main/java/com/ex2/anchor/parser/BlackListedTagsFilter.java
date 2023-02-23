package com.ex2.anchor.parser;

import javax.xml.namespace.QName;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class BlackListedTagsFilter implements StreamFilter {

    private final QName[] blackList;

    public BlackListedTagsFilter(QName... blacklistedNamespaces) {
        this.blackList = blacklistedNamespaces;
    }

    @Override
    public boolean accept(XMLStreamReader reader) {
        if (reader.isStartElement()) {
            QName current = reader.getName();
            for (QName ns : blackList) {
                if (current.equals(ns)) {
                    System.out.println("NS ignored >> " + current);
                    try {
                        while (!(reader.isEndElement() && ns.getLocalPart().equals(reader.getLocalName()))) {
                            reader.next();
                        }
                    } catch (XMLStreamException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static String NS_ITUNES = "http://www.itunes.com/dtds/podcast-1.0.dtd";
    public static String NS_ATOM = "http://www.w3.org/2005/Atom";

    public static BlackListedTagsFilter DEFAULT = new BlackListedTagsFilter(
            new QName(NS_ITUNES, "image"),
            new QName(NS_ATOM, "link")
    );
}