package com.github.mahendranv;

import com.github.mahendranv.model.AnchorResult;
import com.github.mahendranv.model.StatusCode;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class AnchorParserTest {

    String svk = "https://anchor.fm/s/e337170/podcast/rss";

    @Test
    public void test_parse() {
        URL url = getClass().getClassLoader().getResource("feeds/svk_20230220.xml");
        String strUrl = url.toString();
        AnchorResult result = AnchorParser.parse(strUrl);

        System.out.println("Result " + result);

        assertEquals(StatusCode.SUCCESS, result.getStatusCode());
        assertEquals(108, result.getRss().getChannel().getItems().size());
    }
}