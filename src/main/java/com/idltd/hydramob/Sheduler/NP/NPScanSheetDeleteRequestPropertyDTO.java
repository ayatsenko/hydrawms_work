package com.idltd.hydramob.Sheduler.NP;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NPScanSheetDeleteRequestPropertyDTO extends NPMethodProperties {

    @JsonProperty("ScanSheetRefs")
    private List<String> scanSheetRefs;

    public NPScanSheetDeleteRequestPropertyDTO(List<String> scanSheetRefs) {
        super();
        this.scanSheetRefs=scanSheetRefs;
    }

    public List<String> getScanSheetRefs() {
        return scanSheetRefs;
    }
}
