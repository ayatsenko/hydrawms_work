package com.idltd.hydramob.Sheduler.NP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPScanSheetRefsDTO {

    @JsonProperty("Success")
    private List<NPScanSheetDTO> success;

    public NPScanSheetRefsDTO() {
    }

    public List<NPScanSheetDTO> getSuccess() {
        return success;
    }

    public void setSuccess(List<NPScanSheetDTO> success) {
        this.success = success;
    }
}
