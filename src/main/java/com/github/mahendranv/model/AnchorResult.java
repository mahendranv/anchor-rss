package com.github.mahendranv.model;

import lombok.Data;

@Data
public class AnchorResult {

    private Channel channel;

    private StatusCode statusCode;

    private String errorMessage;
}

