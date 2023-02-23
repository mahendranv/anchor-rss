package com.ex2.anchor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Image {
    @JacksonXmlProperty
    private String url;

    @JacksonXmlProperty
    private String title;

    @JacksonXmlProperty
    private String link;
}