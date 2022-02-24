package com.idltd.hydramob.quguar.export.ssl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ITEM")
@XmlAccessorType(XmlAccessType.FIELD)
public class SslItem {
    @XmlElement(required =true)
    private String RECTYP = "";
    @XmlElement(required =true)
    private String ORDER_NR = "";
    @XmlElement(required =true)
    private String POSITION_NR = "";
    @XmlElement(required =true)
    private String PRODUCT_NR = "";
    @XmlElement(required =true)
    private String PROD_SERIAL_NR = "";
    @XmlElement(required =true)
    private String STATUS_QUALITY = "";
    @XmlElement(required =true)
    private String BU_QUANTITY = "";
    @XmlElement(required =true)
    private String DATE_CREATED = "";
    @XmlElement(required =true)
    private String INFO_1 = "";
    @XmlElement(required =true)
    private String SHELFLIFE = "";
    @XmlElement(required =true)
    private String BU_QUANTITY_SECOND = "";
    @XmlElement(required =true)
    private String IS_CROSSDOCK_PICK_BY_LINE = "";
    @XmlElement(required =true)
    private String DOR_C_ORDER_NR = "";

    public String getRECTYP() {
        return RECTYP;
    }

    public void setRECTYP(String RECTYP) {
        this.RECTYP = RECTYP;
    }

    public String getORDER_NR() {
        return ORDER_NR;
    }

    public void setORDER_NR(String ORDER_NR) {
        this.ORDER_NR = ORDER_NR;
    }

    public String getPOSITION_NR() {
        return POSITION_NR;
    }

    public void setPOSITION_NR(String POSITION_NR) {
        this.POSITION_NR = POSITION_NR;
    }

    public String getPRODUCT_NR() {
        return PRODUCT_NR;
    }

    public void setPRODUCT_NR(String PRODUCT_NR) {
        this.PRODUCT_NR = PRODUCT_NR;
    }

    public String getPROD_SERIAL_NR() {
        return PROD_SERIAL_NR;
    }

    public void setPROD_SERIAL_NR(String PROD_SERIAL_NR) {
        this.PROD_SERIAL_NR = PROD_SERIAL_NR;
    }

    public String getSTATUS_QUALITY() {
        return STATUS_QUALITY;
    }

    public void setSTATUS_QUALITY(String STATUS_QUALITY) {
        this.STATUS_QUALITY = STATUS_QUALITY;
    }

    public String getBU_QUANTITY() {
        return BU_QUANTITY;
    }

    public void setBU_QUANTITY(String BU_QUANTITY) {
        this.BU_QUANTITY = BU_QUANTITY;
    }

    public String getDATE_CREATED() {
        return DATE_CREATED;
    }

    public void setDATE_CREATED(String DATE_CREATED) {
        this.DATE_CREATED = DATE_CREATED;
    }

    public String getBU_QUANTITY_SECOND() {
        return BU_QUANTITY_SECOND;
    }

    public void setBU_QUANTITY_SECOND(String BU_QUANTITY_SECOND) {
        this.BU_QUANTITY_SECOND = BU_QUANTITY_SECOND;
    }

    public String getINFO_1() {
        return INFO_1;
    }

    public void setINFO_1(String INFO_1) {
        this.INFO_1 = INFO_1;
    }

    public String getSHELFLIFE() {
        return SHELFLIFE;
    }

    public void setSHELFLIFE(String SHELFLIFE) {
        this.SHELFLIFE = SHELFLIFE;
    }

    public String getIS_CROSSDOCK_PICK_BY_LINE() {
        return IS_CROSSDOCK_PICK_BY_LINE;
    }

    public void setIS_CROSSDOCK_PICK_BY_LINE(String IS_CROSSDOCK_PICK_BY_LINE) {
        this.IS_CROSSDOCK_PICK_BY_LINE = IS_CROSSDOCK_PICK_BY_LINE;
    }

    public String getDOR_C_ORDER_NR() {
        return DOR_C_ORDER_NR;
    }

    public void setDOR_C_ORDER_NR(String DOR_C_ORDER_NR) {
        this.DOR_C_ORDER_NR = DOR_C_ORDER_NR;
    }
}
