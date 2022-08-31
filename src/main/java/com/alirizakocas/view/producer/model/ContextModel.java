package com.alirizakocas.view.producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContextModel {
    @JsonProperty("source")
    private String source;

    public String getSource() {
        return source;
    }
}
