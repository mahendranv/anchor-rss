package com.github.mahendranv.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.mahendranv.parser.AnchorBooleanDeSerializer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Channel {
    @JacksonXmlProperty(localName = "title")
    private String title;

    @JsonIgnore
    private String feedUrl;

    @JsonIgnore
    private String link;

    @JacksonXmlProperty(localName = "description")
    private String description;

    @JsonIgnore
    private String category;

    @JsonIgnore
    private String image;

    @JacksonXmlProperty(localName = "explicit")
    @JsonDeserialize(using = AnchorBooleanDeSerializer.class)
    private boolean explicit;

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Item> items;

    @JacksonXmlProperty(localName = "lastBuildDate")
    @JsonProperty("lastBuildDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy HH:mm:ss Z")
    private Date lastBuildDate;

    // Conflict resolution
    @JsonAnySetter
    public void processUnknown(String name, Object value) {
        if ("image".equals(name) && (value instanceof Map)) {
            Map<?,?> map = (Map<?, ?>) value;
            if (map.containsKey("href")) {
                image = (String) map.get("href");
            } else if (map.containsKey("url")) {
                image = (String) map.get("url");
            }
        }
        if ("link".equals(name) && value instanceof String) {
            link = (String) value;
        }
        if ("category".equals(name) && value instanceof Map) {
            if (((Map<?, ?>) value).containsKey("text")) {
                category = (String) ((Map<?, ?>) value).get("text");
            }
        }
    }
}