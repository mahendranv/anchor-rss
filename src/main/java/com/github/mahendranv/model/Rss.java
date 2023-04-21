package com.github.mahendranv.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "rss")
public class Rss {

    @JacksonXmlProperty(localName = "channel")
    private Channel channel;
}