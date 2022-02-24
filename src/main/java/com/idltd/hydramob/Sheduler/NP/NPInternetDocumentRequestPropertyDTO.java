package com.idltd.hydramob.Sheduler.NP;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NPInternetDocumentRequestPropertyDTO extends NPMethodProperties {

    @JsonProperty("DateTimeFrom")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private final Date dateTimeFrom;

    @JsonProperty("DateTimeTo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private final Date dateTimeTo;

    @JsonProperty("Page")
    private final int page;

    @JsonProperty("GetFullList")
    private final int getFullList;

    public NPInternetDocumentRequestPropertyDTO(Date dateTimeFrom, Date dateTimeTo, int page, int getFullList) {
        super();
        this.dateTimeFrom=dateTimeFrom;
        this.dateTimeTo=dateTimeTo;
        this.page=page;
        this.getFullList=getFullList;
    }
}
