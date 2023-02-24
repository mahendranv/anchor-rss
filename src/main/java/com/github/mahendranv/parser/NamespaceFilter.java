package com.github.mahendranv.parser;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamReader;

public class NamespaceFilter implements StreamFilter {
    
    private final String[] blacklistedNamespaces;

    public NamespaceFilter(String... blacklistedNamespaces) {
        this.blacklistedNamespaces = blacklistedNamespaces;
    }

    @Override
    public boolean accept(XMLStreamReader reader) {
        if (reader.isStartElement()) {
            String namespaceURI = reader.getNamespaceURI();
            for (String blacklistedNamespace : blacklistedNamespaces) {
                if (namespaceURI != null && namespaceURI.startsWith(blacklistedNamespace)) {
                    return false;
                }
            }
        }
        return true;
    }
}