package com.ex2.anchor;

import com.ex2.anchor.model.AnchorResult;
import com.ex2.anchor.model.Rss;
import com.ex2.anchor.model.StatusCode;
import com.ex2.anchor.parser.BlackListedTagsFilter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AnchorParser {

    public static AnchorResult parse(String urlString) {
        AnchorResult result = new AnchorResult();
        XmlMapper mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            XMLInputFactory inputFactory = XMLInputFactory.newFactory();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(new URL(urlString).openStream());
            XMLStreamReader filtered = inputFactory.createFilteredReader(reader, BlackListedTagsFilter.DEFAULT);
            Rss rss = mapper.readValue(filtered, Rss.class);
            result.setRss(rss);
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