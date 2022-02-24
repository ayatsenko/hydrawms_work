package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CUSTOMER")
@XmlAccessorType(XmlAccessType.FIELD)
public class BATXMLDirectSKHCustomer {
    @XmlElement(name = "RECTYP")
    @Column(name = "RECTYP")
    private String rectyp;

    @XmlElement(name = "FIRM_NR")
    @Column(name = "FIRM_NR")
    private String firm_nr;

    @XmlElement(name = "NAME")
    @Column(name = "NAME")
    private String name;

    @XmlElement(name = "NAME_LONG")
    @Column(name = "NAME_LONG")
    private String name_long;

    @XmlElement(name = "FIRM_TYPE_NR")
    @Column(name = "FIRM_TYPE_NR")
    private String firm_type_nr;

    @XmlElement(name = "FIRM_GR_NR")
    @Column(name = "FIRM_GR_NR")
    private String firm_gr_nr;

    @XmlElement(name = "INFO_1")
    @Column(name = "INFO_1")
    private String info_1;

    @XmlElement(name = "ADR_STREET")
    @Column(name = "ADR_STREET")
    private String adr_street;

    @XmlElement(name = "ADR_CITY")
    @Column(name = "ADR_CITY")
    private String adr_city;

    @XmlElement(name = "ADR_ZIPCODE")
    @Column(name = "ADR_ZIPCODE")
    private String adr_zipcode;

    @XmlElement(name = "ADR_POSTBOX")
    @Column(name = "ADR_POSTBOX")
    private String adr_postbox;

    @XmlElement(name = "ADR_COUNTRY_CODE")
    @Column(name = "ADR_COUNTRY_CODE")
    private String adr_country_code;

    @XmlElement(name = "TELEFON_NR_1")
    @Column(name = "TELEFON_NR_1")
    private String telefon_nr_1;

    @XmlElement(name = "FAX_NR_1")
    @Column(name = "FAX_NR_1")
    private String fax_nr_1;

    @XmlElement(name = "INFO_EMAIL")
    @Column(name = "INFO_EMAIL")
    private String info_email;

    @XmlElement(name = "CONTACT_PERS")
    @Column(name = "CONTACT_PERS")
    private String contact_pers;

    @XmlElement(name = "NIP")
    @Column(name = "NIP")
    private String nip;

    @XmlElement(name = "REGON")
    @Column(name = "REGON")
    private String regon;

    @XmlElement(name = "ABC_CLASS")
    @Column(name = "ABC_CLASS")
    private String abc_class;

    @XmlElement(name = "IS_ORDERER")
    @Column(name = "IS_ORDERER")
    private String is_orderer;

    @XmlElement(name = "IS_SUPPLIER")
    @Column(name = "IS_SUPPLIER")
    private String is_supplier;

    @XmlElement(name = "IS_CLIENT")
    @Column(name = "IS_CLIENT")
    private String is_client;

    @XmlElement(name = "IS_CARRIER")
    @Column(name = "IS_CARRIER")
    private String is_carrier;

    @XmlElement(name = "SCAN_GROUP_LABEL")
    @Column(name = "SCAN_GROUP_LABEL")
    private String scan_group_label;

    public String getRectyp() {
        return rectyp;
    }

    public void setRectyp(String rectyp) {
        this.rectyp = rectyp;
    }

    public String getFirm_nr() {
        return firm_nr;
    }

    public void setFirm_nr(String firm_nr) {
        this.firm_nr = firm_nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_long() {
        return name_long;
    }

    public void setName_long(String name_long) {
        this.name_long = name_long;
    }

    public String getFirm_type_nr() {
        return firm_type_nr;
    }

    public void setFirm_type_nr(String firm_type_nr) {
        this.firm_type_nr = firm_type_nr;
    }

    public String getFirm_gr_nr() {
        return firm_gr_nr;
    }

    public void setFirm_gr_nr(String firm_gr_nr) {
        this.firm_gr_nr = firm_gr_nr;
    }

    public String getInfo_1() {
        return info_1;
    }

    public void setInfo_1(String info_1) {
        this.info_1 = info_1;
    }

    public String getAdr_street() {
        return adr_street;
    }

    public void setAdr_street(String adr_street) {
        this.adr_street = adr_street;
    }

    public String getAdr_city() {
        return adr_city;
    }

    public void setAdr_city(String adr_city) {
        this.adr_city = adr_city;
    }

    public String getAdr_zipcode() {
        return adr_zipcode;
    }

    public void setAdr_zipcode(String adr_zipcode) {
        this.adr_zipcode = adr_zipcode;
    }

    public String getAdr_postbox() {
        return adr_postbox;
    }

    public void setAdr_postbox(String adr_postbox) {
        this.adr_postbox = adr_postbox;
    }

    public String getAdr_country_code() {
        return adr_country_code;
    }

    public void setAdr_country_code(String adr_country_code) {
        this.adr_country_code = adr_country_code;
    }

    public String getTelefon_nr_1() {
        return telefon_nr_1;
    }

    public void setTelefon_nr_1(String telefon_nr_1) {
        this.telefon_nr_1 = telefon_nr_1;
    }

    public String getFax_nr_1() {
        return fax_nr_1;
    }

    public void setFax_nr_1(String fax_nr_1) {
        this.fax_nr_1 = fax_nr_1;
    }

    public String getInfo_email() {
        return info_email;
    }

    public void setInfo_email(String info_email) {
        this.info_email = info_email;
    }

    public String getContact_pers() {
        return contact_pers;
    }

    public void setContact_pers(String contact_pers) {
        this.contact_pers = contact_pers;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getAbc_class() {
        return abc_class;
    }

    public void setAbc_class(String abc_class) {
        this.abc_class = abc_class;
    }

    public String getIs_orderer() {
        return is_orderer;
    }

    public void setIs_orderer(String is_orderer) {
        this.is_orderer = is_orderer;
    }

    public String getIs_supplier() {
        return is_supplier;
    }

    public void setIs_supplier(String is_supplier) {
        this.is_supplier = is_supplier;
    }

    public String getIs_client() {
        return is_client;
    }

    public void setIs_client(String is_client) {
        this.is_client = is_client;
    }

    public String getIs_carrier() {
        return is_carrier;
    }

    public void setIs_carrier(String is_carrier) {
        this.is_carrier = is_carrier;
    }

    public String getScan_group_label() {
        return scan_group_label;
    }

    public void setScan_group_label(String scan_group_label) {
        this.scan_group_label = scan_group_label;
    }
}
