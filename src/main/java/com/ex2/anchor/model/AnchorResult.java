package com.ex2.anchor.model;

import lombok.Data;

@Data
public class AnchorResult {

    private Rss rss;

    private StatusCode statusCode;

    private String errorMessage;
}

