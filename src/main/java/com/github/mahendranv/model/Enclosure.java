package com.github.mahendranv.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enclosure {
    @JacksonXmlProperty(isAttribute = true)
    private String url;

    @JacksonXmlProperty(isAttribute = true)
    private String type;
}