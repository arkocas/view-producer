package com.alirizakocas.view.producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ProductViewModel {
    @JsonProperty("event")
    private String event;
    @JsonProperty("messageid")
    private String messageid;
    @JsonProperty("userid")
    private String userid;
    @JsonProperty("properties")
    private PropertiesModel properties;
    @JsonProperty("context")
    private ContextModel context;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());


    public String getEvent() {
        return event;
    }

    public String getMessageid() {
        return messageid;
    }

    public String getUserid() {
        return userid;
    }

    public PropertiesModel getProperties() {
        return properties;
    }
    public ContextModel getContext() {
        return context;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
