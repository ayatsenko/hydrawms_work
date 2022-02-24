package com.idltd.hydramob.Sheduler.NP;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NPRequest {
    @JsonProperty("apiKey")
    private String apiKey;
    @JsonProperty("modelName")
    private String modelName;
    @JsonProperty("calledMethod")
    private String calledMethod;
    @JsonProperty("methodProperties")
    private NPMethodProperties methodProperties;

    public NPRequest() {
    }

    public NPRequest(String apiKey, String modelName, String calledMethod, NPMethodProperties methodProperties) {
        this.apiKey = apiKey;
        this.modelName = modelName;
        this.calledMethod = calledMethod;
        this.methodProperties = methodProperties;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getModelName() {
        return modelName;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public NPMethodProperties getMethodProperties() {
        return methodProperties;
    }
}
