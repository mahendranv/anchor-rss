package com.github.mahendranv.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Locale;

public class AnchorBooleanDeSerializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getValueAsString();
        if (value == null) {
            return false;
        } else {
            return value.toLowerCase(Locale.UK).equals("true") || value.toLowerCase(Locale.UK).equals("yes") || value.equals("1");
        }
    }
}