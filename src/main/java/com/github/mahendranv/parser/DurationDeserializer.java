package com.github.mahendranv.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.github.mahendranv.utils.SecondsConverter;

import java.io.IOException;

public class DurationDeserializer extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (value == null) {
            return 0L;
        } else {
            return SecondsConverter.stringToSeconds(value);
        }
    }
}
