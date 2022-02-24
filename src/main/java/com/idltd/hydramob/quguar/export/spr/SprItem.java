package com.idltd.hydramob.quguar.export.spr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ITEM")
@XmlAccessorType(XmlAccessType.FIELD)
public class SprItem {
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
    private String AVERAGE_WEIGHT_UNIT = "";
    @XmlElement(required =true)
    private String BU_QUANTITY_SECOND = "";
    @XmlElement(required =true)
    private String ITEMBARCODE = "";

    public String getINFO_1() {
        return INFO_1;
    }

    public void setINFO_1(String INFO_1) {
        this.INFO_1 = INFO_1;
    }

    public String getITEMBARCODE() {
        return ITEMBARCODE;
    }

    public void setITEMBARCODE(String ITEMBARCODE) {
        this.ITEMBARCODE = ITEMBARCODE;
    }

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

    public String getAVERAGE_WEIGHT_UNIT() {
        return AVERAGE_WEIGHT_UNIT;
    }

    public void setAVERAGE_WEIGHT_UNIT(String AVERAGE_WEIGHT_UNIT) {
        this.AVERAGE_WEIGHT_UNIT = AVERAGE_WEIGHT_UNIT;
    }

    public String getBU_QUANTITY_SECOND() {
        return BU_QUANTITY_SECOND;
    }

    public void setBU_QUANTITY_SECOND(String BU_QUANTITY_SECOND) {
        this.BU_QUANTITY_SECOND = BU_QUANTITY_SECOND;
    }
}
