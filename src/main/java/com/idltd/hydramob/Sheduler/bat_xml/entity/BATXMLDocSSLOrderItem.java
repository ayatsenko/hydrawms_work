package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ITEM")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDocSSLOrderItem {
    @XmlElement(name = "RECTYP")
    @Column(name = "RECTYP")
    private String rectyp;

    @XmlElement(name = "ORDER_NR")
    @Column(name = "ORDER_NR")
    private String order_nr;

    @XmlElement(name = "POSITION_NR")
    @Column(name = "POSITION_NR")
    private String position_nr;

    @XmlElement(name = "PRODUCT_NR")
    @Column(name = "PRODUCT_NR")
    private String product_nr;

    @XmlElement(name = "PROD_SERIAL_NR")
    @Column(name = "PROD_SERIAL_NR")
    private String prod_serial_nr;

    @XmlElement(name = "STATUS_QUALITY")
    @Column(name = "STATUS_QUALITY")
    private String status_quality;

    @XmlElement(name = "BU_QUANTITY")
    @Column(name = "BU_QUANTITY")
    private String bu_quantity;

    @XmlElement(name = "DATE_CREATED")
    @Column(name = "DATE_CREATED")
    private String date_created;

    public String getRectyp() {
        return rectyp;
    }

    public void setRectyp(String rectyp) {
        this.rectyp = rectyp;
    }

    public String getOrder_nr() {
        return order_nr;
    }

    public void setOrder_nr(String order_nr) {
        this.order_nr = order_nr;
    }

    public String getPosition_nr() {
        return position_nr;
    }

    public void setPosition_nr(String position_nr) {
        this.position_nr = position_nr;
    }

    public String getProduct_nr() {
        return product_nr;
    }

    public void setProduct_nr(String product_nr) {
        this.product_nr = product_nr;
    }

    public String getProd_serial_nr() {
        return prod_serial_nr;
    }

    public void setProd_serial_nr(String prod_serial_nr) {
        this.prod_serial_nr = prod_serial_nr;
    }

    public String getStatus_quality() {
        return status_quality;
    }

    public void setStatus_quality(String status_quality) {
        this.status_quality = status_quality;
    }

    public String getBu_quantity() {
        return bu_quantity;
    }

    public void setBu_quantity(String bu_quantity) {
        this.bu_quantity = bu_quantity;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
