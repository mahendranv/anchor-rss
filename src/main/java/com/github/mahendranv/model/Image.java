package com.github.mahendranv.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class Image {
    @JacksonXmlProperty
    private String url;

    @JacksonXmlProperty
    private String title;

    @JacksonXmlProperty
    private String link;
}