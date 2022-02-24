package com.idltd.hydramob.Sheduler.NP;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NPScanSheetRequestPropertyDTO extends NPMethodProperties {

    @JsonProperty("DocumentRefs")
    private List<String> documentRefs;

    public NPScanSheetRequestPropertyDTO(List<String> documentRefs) {
        super();
        this.documentRefs=documentRefs;
    }

    public List<String> getDocumentRefs() {
        return documentRefs;
    }

    public void setDocumentRefs(List<String> documentRefs) {
        this.documentRefs = documentRefs;
    }
}
