package com.github.mahendranv;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.mahendranv.model.AnchorResult;
import com.github.mahendranv.model.Rss;
import com.github.mahendranv.model.StatusCode;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AnchorParser {

    public static AnchorResult parse(String urlString) {
        AnchorResult result = new AnchorResult();
        XmlMapper mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            URL url = new URL(urlString);
            Rss rss = mapper.readValue(url, Rss.class);
            rss.getChannel().setFeedUrl(urlString);
            result.setChannel(rss.getChannel());
            result.setStatusCode(StatusCode.SUCCESS);
        } catch (MalformedURLException e) {
            result.setStatusCode(StatusCode.INVALID_URL);
            handleException(result, e);
        } catch (IOException e) {
            result.setStatusCode(StatusCode.IO_EXCEPTION);
            handleException(result, e);
        } catch (Exception e) {
            result.setStatusCode(StatusCode.PARSER_FAILURE);
            handleException(result, e);
        }
        return result;
    }

    private static void handleException(AnchorResult result, Exception e) {
        result.setErrorMessage(e.getMessage());
        e.printStackTrace();
    }
}