package com.github.mahendranv;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.mahendranv.model.AnchorResult;
import com.github.mahendranv.model.Rss;
import com.github.mahendranv.model.StatusCode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AnchorParser {

    private static XmlFactory xmlFactory;

    public static void setFactory(XmlFactory factory) {
        xmlFactory = factory;
    }

    public static AnchorResult parse(String urlString) {
        AnchorResult result = new AnchorResult();
        final XmlMapper mapper;
        if (xmlFactory != null) {
            mapper = new XmlMapper(xmlFactory);
        } else {
            mapper = new XmlMapper();
        }
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            URL url = new URL(urlString);
            Rss rss = mapper.readValue(url, Rss.class);
            if (rss.getChannel() == null) {
                // Invalid state - possible case is well-formed XML - but not a rss
                throw new JsonParseException("Failed to parse " + url);
            }
            rss.getChannel().setFeedUrl(urlString);
            result.setChannel(rss.getChannel());
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (JsonParseException e) {
            result.setStatusCode(StatusCode.PARSER_FAILURE);
            handleException(result, e);
        } catch (MalformedURLException e) {
            result.setStatusCode(StatusCode.INVALID_URL);
            handleException(result, e);
        } catch (IOException e) {
            result.setStatusCode(StatusCode.IO_EXCEPTION);
            handleException(result, e);
        } catch (Exception e) {
            result.setStatusCode(StatusCode.UNKNOWN);
            handleException(result, e);
        }
        return result;
    }

    private static void handleException(AnchorResult result, Exception e) {
        result.setErrorMessage(e.getMessage());
        e.printStackTrace();
    }
}