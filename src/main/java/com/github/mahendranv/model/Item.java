package com.github.mahendranv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.mahendranv.parser.AnchorBooleanDeSerializer;
import com.github.mahendranv.parser.DurationDeserializer;
import com.github.mahendranv.utils.SecondsConverter;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
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

    @JacksonXmlProperty(localName = "duration")
    @JsonProperty("duration")
    @JsonDeserialize(using = DurationDeserializer.class)
    private long durationInSeconds;

    @JacksonXmlProperty(localName = "enclosure")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Enclosure> enclosures;

    public String getPrintableDuration() {
        return SecondsConverter.secondsToString(durationInSeconds);
    }

    /**
     * Runs basic integrity on the fields and returns whether the item meets the requirements.
     */
    public boolean isValid() {
        Enclosure stream = getAudioEnclosure();
        return stream != null && stream.getUrl() != null;
    }

    /**
     * There are feeds which includes other attachments as part of episode. In such cases, iterate
     * through the enclosures and pick the audio stream.
     *
     * @return the audio enclosure.
     */
    public Enclosure getAudioEnclosure() {
        Enclosure result = null;
        for (Enclosure e : enclosures) {
            if (e.getType().toLowerCase().startsWith("audio")) {
                result = e;
                break;
            }
        }
        return result;
    }

    /**
     * @return the audio stream url - `null` if no audio type enclosure found.
     */
    public String getStreamUrl() {
        Enclosure audio = getAudioEnclosure();
        if (audio == null) {
            return null;
        } else {
            return audio.getUrl();
        }
    }
}