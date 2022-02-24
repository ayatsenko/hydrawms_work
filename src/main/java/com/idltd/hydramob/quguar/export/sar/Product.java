package com.idltd.hydramob.quguar.export.sar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PRODUCT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    @XmlElement(required =true)
    private String RECTYP = "";
    @XmlElement(required =true)
    private String UNIT_NR = "";
    @XmlElement(required =true)
    private String PROD_CLASS_NR = "";
    @XmlElement(required =true)
    private String PROD_GROUP_NR = "";
    @XmlElement(required =true)
    private String PROD_TYPE = "";
    @XmlElement(required =true)
    private String PROD_NR = "";
    @XmlElement(required =true)
    private String PROD_NAME = "";
    @XmlElement(required =true)
    private String INFO1 = "";
    @XmlElement(required =true)
    private String EAN = "";
    @XmlElement(required =true)
    private String IS_SERIAL = "";
    @XmlElement(required =true)
    private String ABC_CLASS = "";
    @XmlElement(required =true)
    private String PROD_EXPIRES = "";
    @XmlElement(required =true)
    private String EXPIRE_PERIOD = "";
    @XmlElement(required =true)
    private String HAS_SERIAL_NUMBER = "";
    @XmlElement(required =true)
    private String IS_SCAN_SERIAL_IN = "";
    @XmlElement(required =true)
    private String IS_SCAN_SERIAL_OUT = "";
    @XmlElement(required =true)
    private String IS_ECO_CERTIFICATE = "";
    @XmlElement(required =true)
    private String IS_NUMBER_EXCISE = "";
    @XmlElement(required =true)
    private String IS_AUTO_GEN_PROD_SERIAL = "";
    @XmlElement(required =true)
    private String IS_WEIGHT = "";
    @XmlElement(required =true)
    private String IS_PIECE_WEIGHT = "";
    @XmlElement(required =true)
    private String IS_AVERAGE_WEIGHT_UNIT = "";
    @XmlElement(required =true)
    private String PICK_WEIGHT_TOLERANCE = "";
    @XmlElement(required =true)
    private String IS_CROSSDOCK_PICK_BY_LINE = "";
    @XmlElement(required =true)
    private String DEFAULT_OWNER_NR = "";
    @XmlElement(required =true)
    private String IS_CODE_REQ = "";
    @XmlElement(required =true)
    private String UNLOAD_TYPE = "";

    public String getRECTYP() {
        return RECTYP;
    }

    public void setRECTYP(String RECTYP) {
        this.RECTYP = RECTYP;
    }

    public String getUNIT_NR() {
        return UNIT_NR;
    }

    public void setUNIT_NR(String UNIT_NR) {
        this.UNIT_NR = UNIT_NR;
    }

    public String getPROD_CLASS_NR() {
        return PROD_CLASS_NR;
    }

    public void setPROD_CLASS_NR(String PROD_CLASS_NR) {
        this.PROD_CLASS_NR = PROD_CLASS_NR;
    }

    public String getPROD_GROUP_NR() {
        return PROD_GROUP_NR;
    }

    public void setPROD_GROUP_NR(String PROD_GROUP_NR) {
        this.PROD_GROUP_NR = PROD_GROUP_NR;
    }

    public String getPROD_TYPE() {
        return PROD_TYPE;
    }

    public void setPROD_TYPE(String PROD_TYPE) {
        this.PROD_TYPE = PROD_TYPE;
    }

    public String getPROD_NR() {
        return PROD_NR;
    }

    public void setPROD_NR(String PROD_NR) {
        this.PROD_NR = PROD_NR;
    }

    public String getPROD_NAME() {
        return PROD_NAME;
    }

    public void setPROD_NAME(String PROD_NAME) {
        this.PROD_NAME = PROD_NAME;
    }

    public String getINFO1() {
        return INFO1;
    }

    public void setINFO1(String INFO1) {
        this.INFO1 = INFO1;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getIS_SERIAL() {
        return IS_SERIAL;
    }

    public void setIS_SERIAL(String IS_SERIAL) {
        this.IS_SERIAL = IS_SERIAL;
    }

    public String getABC_CLASS() {
        return ABC_CLASS;
    }

    public void setABC_CLASS(String ABC_CLASS) {
        this.ABC_CLASS = ABC_CLASS;
    }

    public String getPROD_EXPIRES() {
        return PROD_EXPIRES;
    }

    public void setPROD_EXPIRES(String PROD_EXPIRES) {
        this.PROD_EXPIRES = PROD_EXPIRES;
    }

    public String getEXPIRE_PERIOD() {
        return EXPIRE_PERIOD;
    }

    public void setEXPIRE_PERIOD(String EXPIRE_PERIOD) {
        this.EXPIRE_PERIOD = EXPIRE_PERIOD;
    }

    public String getHAS_SERIAL_NUMBER() {
        return HAS_SERIAL_NUMBER;
    }

    public void setHAS_SERIAL_NUMBER(String HAS_SERIAL_NUMBER) {
        this.HAS_SERIAL_NUMBER = HAS_SERIAL_NUMBER;
    }

    public String getIS_SCAN_SERIAL_IN() {
        return IS_SCAN_SERIAL_IN;
    }

    public void setIS_SCAN_SERIAL_IN(String IS_SCAN_SERIAL_IN) {
        this.IS_SCAN_SERIAL_IN = IS_SCAN_SERIAL_IN;
    }

    public String getIS_SCAN_SERIAL_OUT() {
        return IS_SCAN_SERIAL_OUT;
    }

    public void setIS_SCAN_SERIAL_OUT(String IS_SCAN_SERIAL_OUT) {
        this.IS_SCAN_SERIAL_OUT = IS_SCAN_SERIAL_OUT;
    }

    public String getIS_ECO_CERTIFICATE() {
        return IS_ECO_CERTIFICATE;
    }

    public void setIS_ECO_CERTIFICATE(String IS_ECO_CERTIFICATE) {
        this.IS_ECO_CERTIFICATE = IS_ECO_CERTIFICATE;
    }

    public String getIS_NUMBER_EXCISE() {
        return IS_NUMBER_EXCISE;
    }

    public void setIS_NUMBER_EXCISE(String IS_NUMBER_EXCISE) {
        this.IS_NUMBER_EXCISE = IS_NUMBER_EXCISE;
    }

    public String getIS_AUTO_GEN_PROD_SERIAL() {
        return IS_AUTO_GEN_PROD_SERIAL;
    }

    public void setIS_AUTO_GEN_PROD_SERIAL(String IS_AUTO_GEN_PROD_SERIAL) {
        this.IS_AUTO_GEN_PROD_SERIAL = IS_AUTO_GEN_PROD_SERIAL;
    }

    public String getIS_WEIGHT() {
        return IS_WEIGHT;
    }

    public void setIS_WEIGHT(String IS_WEIGHT) {
        this.IS_WEIGHT = IS_WEIGHT;
    }

    public String getIS_PIECE_WEIGHT() {
        return IS_PIECE_WEIGHT;
    }

    public void setIS_PIECE_WEIGHT(String IS_PIECE_WEIGHT) {
        this.IS_PIECE_WEIGHT = IS_PIECE_WEIGHT;
    }

    public String getIS_AVERAGE_WEIGHT_UNIT() {
        return IS_AVERAGE_WEIGHT_UNIT;
    }

    public void setIS_AVERAGE_WEIGHT_UNIT(String IS_AVERAGE_WEIGHT_UNIT) {
        this.IS_AVERAGE_WEIGHT_UNIT = IS_AVERAGE_WEIGHT_UNIT;
    }

    public String getPICK_WEIGHT_TOLERANCE() {
        return PICK_WEIGHT_TOLERANCE;
    }

    public void setPICK_WEIGHT_TOLERANCE(String PICK_WEIGHT_TOLERANCE) {
        this.PICK_WEIGHT_TOLERANCE = PICK_WEIGHT_TOLERANCE;
    }

    public String getIS_CROSSDOCK_PICK_BY_LINE() {
        return IS_CROSSDOCK_PICK_BY_LINE;
    }

    public void setIS_CROSSDOCK_PICK_BY_LINE(String IS_CROSSDOCK_PICK_BY_LINE) {
        this.IS_CROSSDOCK_PICK_BY_LINE = IS_CROSSDOCK_PICK_BY_LINE;
    }

    public String getDEFAULT_OWNER_NR() {
        return DEFAULT_OWNER_NR;
    }

    public void setDEFAULT_OWNER_NR(String DEFAULT_OWNER_NR) {
        this.DEFAULT_OWNER_NR = DEFAULT_OWNER_NR;
    }

    public String getIS_CODE_REQ() {
        return IS_CODE_REQ;
    }

    public void setIS_CODE_REQ(String IS_CODE_REQ) {
        this.IS_CODE_REQ = IS_CODE_REQ;
    }

    public String getUNLOAD_TYPE() {
        return UNLOAD_TYPE;
    }

    public void setUNLOAD_TYPE(String UNLOAD_TYPE) {
        this.UNLOAD_TYPE = UNLOAD_TYPE;
    }
}
