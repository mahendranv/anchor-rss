package com.github.mahendranv;

import com.github.mahendranv.model.AnchorResult;
import com.github.mahendranv.model.Channel;
import com.github.mahendranv.model.StatusCode;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnchorParserTest {

    @Test
    public void test_parse_anchor_svk() {
        AnchorResult result = AnchorParser.parse(TestResources.RSS_SVK);
        Channel channel = assertMandatoryFields(result);
        assertEquals(TestResources.RSS_SVK, channel.getFeedUrl());
        assertEquals(108, result.getChannel().getItems().size());
        assertTrue(channel.isExplicit());
        assertTrue(channel.getItems().get(0).isExplicit());
        assertNotNull(channel.getItems().get(0).getStreamUrl());
        assertEquals("Comedy", channel.getCategory());
    }

    @Test
    public void test_parse_simple_fragmented() {
        AnchorResult result = AnchorParser.parse(TestResources.RSS_FRAGMENTED);
        Channel channel = assertMandatoryFields(result);
        assertFalse(channel.isExplicit());
        assertFalse(channel.getItems().get(0).isExplicit());
        assertNotNull(channel.getItems().get(0).getStreamUrl());
        assertEquals(242, channel.getItems().size());
        assertEquals("Technology", channel.getCategory());
    }

    @Test
    public void test_parse_simple_fragmented_remote() {
        AnchorResult result = AnchorParser.parse(TestResources.RSS_FRAGMENTED_REMOTE);
        Channel channel = assertMandatoryFields(result);
        assertFalse(channel.isExplicit());
        assertFalse(channel.getItems().get(0).isExplicit());
        assertNotNull(channel.getItems().get(0).getStreamUrl());
    }

    /**
     * Validates mandatory fields in the restult and returns the channel
     *
     * @param result result from parser
     * @return the channel
     */
    private Channel assertMandatoryFields(AnchorResult result) {
        assertEquals(StatusCode.SUCCESS, result.getStatusCode());
        Channel channel = result.getChannel();
        assertNotNull("Channel title cannot be null: ", channel.getTitle());
        assertNotNull("Channel image URL is not null: ", channel.getImage());
        assertNotNull("Channel link cannot be null: ", channel.getLink());
        assertNotNull("Channel last build date cannot be null: ", channel.getLastBuildDate());
        return channel;
    }
}