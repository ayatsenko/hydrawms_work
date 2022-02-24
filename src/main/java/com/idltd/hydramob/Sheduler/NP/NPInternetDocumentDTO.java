package com.idltd.hydramob.Sheduler.NP;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NPInternetDocumentDTO {

    @JsonProperty("Ref")
    private String ref;

    @JsonProperty("IntDocNumber")
    private String intDocNumber;

    @JsonProperty("DateTime")
    private String datetime;

    @JsonProperty("ScanSheetNumber")
    private String scanSheetNumber;

    @JsonProperty("RecipientContactPhone")
    private String recipientContactPhone;

    @JsonProperty("RecipientContactPerson")
    private String recipientContactPerson;

    @JsonProperty("CityRecipientDescription")
    private String cityRecipientDescription;

    @JsonProperty("RecipientAddressDescription")
    private String recipientAddressDescription;

    public NPInternetDocumentDTO() {
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getIntDocNumber() {
        return intDocNumber;
    }

    public void setIntDocNumber(String intDocNumber) {
        this.intDocNumber = intDocNumber;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getScanSheetNumber() {
        return scanSheetNumber;
    }

    public void setScanSheetNumber(String scanSheetNumber) {
        this.scanSheetNumber = scanSheetNumber;
    }

    public String getRecipientContactPhone() {
        return recipientContactPhone;
    }

    public void setRecipientContactPhone(String recipientContactPhone) {
        this.recipientContactPhone = recipientContactPhone;
    }

    public String getRecipientContactPerson() {
        return recipientContactPerson;
    }

    public void setRecipientContactPerson(String recipientContactPerson) {
        this.recipientContactPerson = recipientContactPerson;
    }

    public String getCityRecipientDescription() {
        return cityRecipientDescription;
    }

    public void setCityRecipientDescription(String cityRecipientDescription) {
        this.cityRecipientDescription = cityRecipientDescription;
    }

    public String getRecipientAddressDescription() {
        return recipientAddressDescription;
    }

    public void setRecipientAddressDescription(String recipientAddressDescription) {
        this.recipientAddressDescription = recipientAddressDescription;
    }
}
