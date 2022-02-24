package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.*;

@Entity
@Table(name = "EXCHANGE_SERVICE_SKH_TEMP", schema = "HCOMM")
public class ExchangeServiceSkhCustomerTemp {
    @Column(name = "EXCH_SER_FILE_ID")
    public Long exch_ser_file_id;

    @Id
    @Column(name = "EXCH_SER_SKH_ROW_ID", nullable = false)
    @SequenceGenerator(name = "EXCHANGE_SERVICE_SKH_TEMP_SEQ", sequenceName = "EXCHANGE_SERVICE_SKH_TEMP_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_SKH_TEMP_SEQ")
    public Long exch_ser_skh_row_id;

    @Column(name = "RECTYP")
    public String rectyp;

    @Column(name = "FIRM_NR")
    public String firm_nr;

    @Column(name = "NAME")
    public String name;

    @Column(name = "NAME_LONG")
    public String name_long;

    @Column(name = "FIRM_TYPE_NR")
    public String firm_type_nr;

    @Column(name = "FIRM_GR_NR")
    public String firm_gr_nr;

    @Column(name = "INFO_1")
    public String info_1;

    @Column(name = "ADR_STREET")
    public String adr_street;

    @Column(name = "ADR_CITY")
    public String adr_city;

    @Column(name = "ADR_ZIPCODE")
    public String adr_zipcode;

    @Column(name = "ADR_POSTBOX")
    public String adr_postbox;

    @Column(name = "ADR_COUNTRY_CODE")
    public String adr_country_code;

    @Column(name = "TELEFON_NR_1")
    public String telefon_nr_1;

    @Column(name = "FAX_NR_1")
    public String fax_nr_1;

    @Column(name = "INFO_EMAIL")
    public String info_email;

    @Column(name = "CONTACT_PERS")
    public String contact_pers;

    @Column(name = "NIP")
    public String nip;

    @Column(name = "REGON")
    public String regon;

    @Column(name = "ABC_CLASS")
    public String abc_class;

    @Column(name = "IS_ORDERER")
    public String is_orderer;

    @Column(name = "IS_SUPPLIER")
    public String is_supplier;

    @Column(name = "IS_CLIENT")
    public String is_client;

    @Column(name = "IS_CARRIER")
    public String is_carrier;

    @Column(name = "SCAN_GROUP_LABEL")
    public String scan_group_label;

    public Long getExch_ser_file_id() {
        return exch_ser_file_id;
    }

    public void setExch_ser_file_id(Long exch_ser_file_id) {
        this.exch_ser_file_id = exch_ser_file_id;
    }

    public Long getExch_ser_skh_row_id() {
        return exch_ser_skh_row_id;
    }

    public void setExch_ser_skh_row_id(Long exch_ser_skh_row_id) {
        this.exch_ser_skh_row_id = exch_ser_skh_row_id;
    }

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
