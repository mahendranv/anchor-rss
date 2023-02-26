package com.github.mahendranv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.mahendranv.parser.AnchorBooleanDeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Item {

    @JacksonXmlProperty(localName = "title")
    @JsonProperty("title")
    private String title;

    @JacksonXmlProperty(localName = "description")
    @JsonProperty("description")
    private String description;

    @JacksonXmlProperty(localName = "link")
    @JsonProperty("link")
    private String link;

    @JacksonXmlProperty(localName = "guid")
    @JsonProperty("guid")
    private String guid;

    @JacksonXmlProperty(localName = "season")
    @JsonProperty("season")
    private int season;

    @JacksonXmlProperty(localName = "episode")
    @JsonProperty("episode")
    private int episode;

    @JacksonXmlProperty(localName = "explicit")
    @JsonDeserialize(using = AnchorBooleanDeSerializer.class)
    private boolean explicit;

    @JacksonXmlProperty(localName = "pubDate")
    @JsonProperty("pubDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy HH:mm:ss Z")
    private Date pubDate;
}