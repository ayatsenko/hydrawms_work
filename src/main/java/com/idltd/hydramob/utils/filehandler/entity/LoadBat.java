package com.idltd.hydramob.utils.filehandler.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "LOAD_BAT")
public class LoadBat {
    @Id
    @Column(name = "LT_ID")
    @SequenceGenerator(name = "LOAD_BAT_SEQ", sequenceName = "LOAD_BAT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOAD_BAT_SEQ")
    private long lt_id;

    @Column(name = "LT_PART")
    private Long lt_part;

    @Column(name = "LT_CLIENT")
    private String lt_client;

    @Column(name = "CREATE_DATE")
    private Date create_date;

    @Column(name = "CREATE_USER")
    private String create_user;

    @Column(name = "LT_SHEET")
    private Long lt_sheet;

    @Column(name = "LT_DELIVERY")
    private String lt_delivery;

    @Column(name = "LT_PICKINGDATE")
    private String lt_pickingdate;

    @Column(name = "LT_DELIVDATEFROMTO")
    private String lt_delivdatefromto;

    @Column(name = "LT_PURCHASINGDOCUMENT")
    private String lt_purchasingdocument;

    @Column(name = "LT_DELIVERYTYPE")
    private String lt_deliverytype;

    @Column(name = "LT_PROCESSINGSTATUS")
    private String lt_processingstatus;

    @Column(name = "LT_PLANT")
    private String lt_plant;

    @Column(name = "LT_SHIPPINGPOINTRECEIVINGPT")
    private String lt_shippingpointreceivingpt;

    @Column(name = "LT_SOLDTOPARTY")
    private String lt_soldtoparty;

    @Column(name = "LT_STORAGELOCATION")
    private String lt_storagelocation;

    @Column(name = "LT_LOCATIONOFTHESHIPTOPAR")
    private String lt_locationoftheshiptopar;

    @Column(name = "LT_NAMEOFTHESHIPTOPARTY")
    private String lt_nameoftheshiptoparty;

    @Column(name = "LT_MATERIAL")
    private String lt_material;

    @Column(name = "LT_DESCRIPTION")
    private String lt_description;

    @Column(name = "LT_ACTUALDELIVERYQTY")
    private String lt_actualdeliveryqty;

    @Column(name = "LT_BASEUNITOFMEASURE")
    private String lt_baseunitofmeasure;

    @Column(name = "LT_TOTALGDSMVTSTAT")
    private String lt_totalgdsmvtstat;

    @Column(name = "LT_DELIVERYQUANTITY")
    private String lt_deliveryquantity;

    @Column(name = "LT_BATCH")
    private String lt_batch;

    @Column(name = "LT_EXTERNALDELIVERYID")
    private String lt_externaldeliveryid;

    @Column(name = "LT_EXPORTED")
    private Long lt_exported;

    @Column(name = "LT_QFILENUMBER")
    private Long lt_qfilenumber;

    public String getLt_client() {
        return lt_client;
    }

    public void setLt_client(String lt_client) {
        this.lt_client = lt_client;
    }

    public long getLt_id() {
        return lt_id;
    }

    public void setLt_id(long lt_id) {
        this.lt_id = lt_id;
    }

    public Long getLt_part() {
        return lt_part;
    }

    public void setLt_part(Long lt_part) {
        this.lt_part = lt_part;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Long getLt_sheet() {
        return lt_sheet;
    }

    public void setLt_sheet(Long lt_sheet) {
        this.lt_sheet = lt_sheet;
    }

    public String getLt_delivery() {
        return lt_delivery;
    }

    public void setLt_delivery(String lt_delivery) {
        this.lt_delivery = lt_delivery;
    }

    public String getLt_pickingdate() {
        return lt_pickingdate;
    }

    public void setLt_pickingdate(String lt_pickingdate) {
        this.lt_pickingdate = lt_pickingdate;
    }

    public String getLt_delivdatefromto() {
        return lt_delivdatefromto;
    }

    public void setLt_delivdatefromto(String lt_delivdatefromto) {
        this.lt_delivdatefromto = lt_delivdatefromto;
    }

    public String getLt_purchasingdocument() {
        return lt_purchasingdocument;
    }

    public void setLt_purchasingdocument(String lt_purchasingdocument) {
        this.lt_purchasingdocument = lt_purchasingdocument;
    }

    public String getLt_deliverytype() {
        return lt_deliverytype;
    }

    public void setLt_deliverytype(String lt_deliverytype) {
        this.lt_deliverytype = lt_deliverytype;
    }

    public String getLt_processingstatus() {
        return lt_processingstatus;
    }

    public void setLt_processingstatus(String lt_processingstatus) {
        this.lt_processingstatus = lt_processingstatus;
    }

    public String getLt_plant() {
        return lt_plant;
    }

    public void setLt_plant(String lt_plant) {
        this.lt_plant = lt_plant;
    }

    public String getLt_shippingpointreceivingpt() {
        return lt_shippingpointreceivingpt;
    }

    public void setLt_shippingpointreceivingpt(String lt_shippingpointreceivingpt) {
        this.lt_shippingpointreceivingpt = lt_shippingpointreceivingpt;
    }

    public String getLt_soldtoparty() {
        return lt_soldtoparty;
    }

    public void setLt_soldtoparty(String lt_soldtoparty) {
        this.lt_soldtoparty = lt_soldtoparty;
    }

    public String getLt_storagelocation() {
        return lt_storagelocation;
    }

    public void setLt_storagelocation(String lt_storagelocation) {
        this.lt_storagelocation = lt_storagelocation;
    }

    public String getLt_locationoftheshiptopar() {
        return lt_locationoftheshiptopar;
    }

    public void setLt_locationoftheshiptopar(String lt_locationoftheshiptopar) {
        this.lt_locationoftheshiptopar = lt_locationoftheshiptopar;
    }

    public String getLt_nameoftheshiptoparty() {
        return lt_nameoftheshiptoparty;
    }

    public void setLt_nameoftheshiptoparty(String lt_nameoftheshiptoparty) {
        this.lt_nameoftheshiptoparty = lt_nameoftheshiptoparty;
    }

    public String getLt_material() {
        return lt_material;
    }

    public void setLt_material(String lt_material) {
        this.lt_material = lt_material;
    }

    public String getLt_description() {
        return lt_description;
    }

    public void setLt_description(String lt_description) {
        this.lt_description = lt_description;
    }

    public String getLt_actualdeliveryqty() {
        return lt_actualdeliveryqty;
    }

    public void setLt_actualdeliveryqty(String lt_actualdeliveryqty) {
        this.lt_actualdeliveryqty = lt_actualdeliveryqty;
    }

    public String getLt_baseunitofmeasure() {
        return lt_baseunitofmeasure;
    }

    public void setLt_baseunitofmeasure(String lt_baseunitofmeasure) {
        this.lt_baseunitofmeasure = lt_baseunitofmeasure;
    }

    public String getLt_totalgdsmvtstat() {
        return lt_totalgdsmvtstat;
    }

    public void setLt_totalgdsmvtstat(String lt_totalgdsmvtstat) {
        this.lt_totalgdsmvtstat = lt_totalgdsmvtstat;
    }

    public String getLt_batch() {
        return lt_batch;
    }

    public void setLt_batch(String lt_batch) {
        this.lt_batch = lt_batch;
    }

    public String getLt_externaldeliveryid() {
        return lt_externaldeliveryid;
    }

    public void setLt_externaldeliveryid(String lt_externaldeliveryid) {
        this.lt_externaldeliveryid = lt_externaldeliveryid;
    }

    public Long getLt_exported() {
        return lt_exported;
    }

    public void setLt_exported(Long lt_exported) {
        this.lt_exported = lt_exported;
    }

    public Long getLt_qfilenumber() {
        return lt_qfilenumber;
    }

    public void setLt_qfilenumber(Long lt_qfilenumber) {
        this.lt_qfilenumber = lt_qfilenumber;
    }

    public String getLt_deliveryquantity() {
        return lt_deliveryquantity;
    }

    public void setLt_deliveryquantity(String lt_deliveryquantity) {
        this.lt_deliveryquantity = lt_deliveryquantity;
    }
}
