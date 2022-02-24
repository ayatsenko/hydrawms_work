package com.idltd.hydramob.Sheduler.NP;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class NPResponse_dss {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("errors")
    private List<String> errors;
    @JsonProperty("warnings")
    private List<String> warnings;
    @JsonProperty("info")
    private List<String> info;
    @JsonProperty("data")
    private T data;

    public NPResponse_dss() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
