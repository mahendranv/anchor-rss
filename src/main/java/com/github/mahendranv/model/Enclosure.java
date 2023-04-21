package com.github.mahendranv.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class Enclosure {
    @JacksonXmlProperty(localName = "url", isAttribute = true)
    private String url;

    @JacksonXmlProperty(localName = "type", isAttribute = true)
    private String type;

    @JacksonXmlProperty(localName = "length", isAttribute = true)
    private long length;
}