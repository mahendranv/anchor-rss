package com.github.mahendranv.model;

import lombok.Data;

@Data
public class AnchorResult {

    private Rss rss;

    private StatusCode statusCode;

    private String errorMessage;
}

