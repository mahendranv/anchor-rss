package com.github.mahendranv;

import com.github.mahendranv.model.AnchorResult;
import com.github.mahendranv.model.Channel;
import com.github.mahendranv.model.StatusCode;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AnchorParserTest {

    String svk = "https://anchor.fm/s/e337170/podcast/rss";

    @Test
    public void test_parse() {
        URL url = getClass().getClassLoader().getResource("feeds/svk_20230220.xml");
        String strUrl = url.toString();

        AnchorResult result = AnchorParser.parse(strUrl);
        Channel channel = result.getRss().getChannel();
        System.out.println("Result " + result);
        assertEquals(StatusCode.SUCCESS, result.getStatusCode());
        assertNotNull("Channel image URL is not null: ", channel.getImage());
        assertNotNull("Channel link cannot be null: ", channel.getLink());
        assertEquals(108, channel.getItems().size());
    }
}