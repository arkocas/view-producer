package com.alirizakocas.view.producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertiesModel {
    @JsonProperty("productid")
    private String productid;

    public String getProductid() {
        return productid;
    }
}
