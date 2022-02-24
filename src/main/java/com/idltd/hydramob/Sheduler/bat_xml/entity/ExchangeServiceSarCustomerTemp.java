package com.idltd.hydramob.Sheduler.bat_xml.entity;

import javax.persistence.*;

@Entity
@Table(name = "EXCHANGE_SERVICE_SAR_TEMP", schema = "HCOMM")
public class ExchangeServiceSarCustomerTemp {
    @Column(name = "EXCH_SER_FILE_ID")
    public Long exch_ser_file_id;

    @Id
    @Column(name = "EXCH_SER_SAR_ROW_ID", nullable = false)
    @SequenceGenerator(name = "EXCHANGE_SERVICE_SAR_TEMP_SEQ", sequenceName = "EXCHANGE_SERVICE_SAR_TEMP_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_SAR_TEMP_SEQ")
    public Long exch_ser_sar_row_id;

    @Column(name = "RECTYP")
    public String rectyp;

    @Column(name = "PROD_NR")
    public String prod_nr;

    @Column(name = "UNIT_NR")
    public String unit_nr;

    @Column(name = "PROD_CLASS_NR")
    public String prod_class_nr;

    @Column(name = "PROD_GROUP_NR")
    public String prod_group_nr;

    @Column(name = "PROD_NAME")
    public String prod_name;

    @Column(name = "INFO1")
    public String info1;

    @Column(name = "EAN")
    public String ean;

    @Column(name = "IS_SERIAL")
    public String is_serial;

    @Column(name = "ABC_CLASS")
    public String abc_class;

    @Column(name = "PROD_EXPIRES")
    public String prod_expires;

    @Column(name = "EXPIRE_PERIOD")
    public String expire_period;

    @Column(name = "HAS_SERIAL_NUMBER")
    public String has_serial_number;

    @Column(name = "IS_SCAN_SERIAL_IN")
    public String is_scan_serial_in;

    @Column(name = "IS_SCAN_SERIAL_OUT")
    public String is_scan_serial_out;

    @Column(name = "IS_ECO_CERTIFICATE")
    public String is_eco_certificate;

    @Column(name = "IS_NUMBER_EXCISE")
    public String is_number_excise;

    @Column(name = "IS_AUTO_GEN_PROD_SERIAL")
    public String is_auto_gen_prod_serial;

    @Column(name = "IS_WEIGHT")
    public String is_weight;

    @Column(name = "IS_PIECE_WEIGHT")
    public String is_piece_weight;

    @Column(name = "IS_AVERAGE_WEIGHT_UNIT")
    public String is_average_weight_unit;

    @Column(name = "PICK_WEIGHT_TOLERANCE")
    public String pick_weight_tolerance;

    @Column(name = "IS_CROSSDOCK_PICK_BY_LINE")
    public String is_crossdock_pick_by_line;

    @Column(name = "DEFAULT_OWNER_NR")
    public String default_owner_nr;

    @Column(name = "IS_CODE_REQ")
    public String is_code_req;

    @Column(name = "UNLOAD_TYPE")
    public String unload_type;

    public Long getExch_ser_file_id() {
        return exch_ser_file_id;
    }

    public void setExch_ser_file_id(Long exch_ser_file_id) {
        this.exch_ser_file_id = exch_ser_file_id;
    }

    public Long getExch_ser_sar_row_id() {
        return exch_ser_sar_row_id;
    }

    public void setExch_ser_sar_row_id(Long exch_ser_sar_row_id) {
        this.exch_ser_sar_row_id = exch_ser_sar_row_id;
    }

    public String getRectyp() {
        return rectyp;
    }

    public void setRectyp(String rectyp) {
        this.rectyp = rectyp;
    }

    public String getProd_nr() {
        return prod_nr;
    }

    public void setProd_nr(String prod_nr) {
        this.prod_nr = prod_nr;
    }

    public String getUnit_nr() {
        return unit_nr;
    }

    public void setUnit_nr(String unit_nr) {
        this.unit_nr = unit_nr;
    }

    public String getProd_class_nr() {
        return prod_class_nr;
    }

    public void setProd_class_nr(String prod_class_nr) {
        this.prod_class_nr = prod_class_nr;
    }

    public String getProd_group_nr() {
        return prod_group_nr;
    }

    public void setProd_group_nr(String prod_group_nr) {
        this.prod_group_nr = prod_group_nr;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getIs_serial() {
        return is_serial;
    }

    public void setIs_serial(String is_serial) {
        this.is_serial = is_serial;
    }

    public String getAbc_class() {
        return abc_class;
    }

    public void setAbc_class(String abc_class) {
        this.abc_class = abc_class;
    }

    public String getProd_expires() {
        return prod_expires;
    }

    public void setProd_expires(String prod_expires) {
        this.prod_expires = prod_expires;
    }

    public String getExpire_period() {
        return expire_period;
    }

    public void setExpire_period(String expire_period) {
        this.expire_period = expire_period;
    }

    public String getHas_serial_number() {
        return has_serial_number;
    }

    public void setHas_serial_number(String has_serial_number) {
        this.has_serial_number = has_serial_number;
    }

    public String getIs_scan_serial_in() {
        return is_scan_serial_in;
    }

    public void setIs_scan_serial_in(String is_scan_serial_in) {
        this.is_scan_serial_in = is_scan_serial_in;
    }

    public String getIs_scan_serial_out() {
        return is_scan_serial_out;
    }

    public void setIs_scan_serial_out(String is_scan_serial_out) {
        this.is_scan_serial_out = is_scan_serial_out;
    }

    public String getIs_eco_certificate() {
        return is_eco_certificate;
    }

    public void setIs_eco_certificate(String is_eco_certificate) {
        this.is_eco_certificate = is_eco_certificate;
    }

    public String getIs_number_excise() {
        return is_number_excise;
    }

    public void setIs_number_excise(String is_number_excise) {
        this.is_number_excise = is_number_excise;
    }

    public String getIs_auto_gen_prod_serial() {
        return is_auto_gen_prod_serial;
    }

    public void setIs_auto_gen_prod_serial(String is_auto_gen_prod_serial) {
        this.is_auto_gen_prod_serial = is_auto_gen_prod_serial;
    }

    public String getIs_weight() {
        return is_weight;
    }

    public void setIs_weight(String is_weight) {
        this.is_weight = is_weight;
    }

    public String getIs_piece_weight() {
        return is_piece_weight;
    }

    public void setIs_piece_weight(String is_piece_weight) {
        this.is_piece_weight = is_piece_weight;
    }

    public String getIs_average_weight_unit() {
        return is_average_weight_unit;
    }

    public void setIs_average_weight_unit(String is_average_weight_unit) {
        this.is_average_weight_unit = is_average_weight_unit;
    }

    public String getPick_weight_tolerance() {
        return pick_weight_tolerance;
    }

    public void setPick_weight_tolerance(String pick_weight_tolerance) {
        this.pick_weight_tolerance = pick_weight_tolerance;
    }

    public String getIs_crossdock_pick_by_line() {
        return is_crossdock_pick_by_line;
    }

    public void setIs_crossdock_pick_by_line(String is_crossdock_pick_by_line) {
        this.is_crossdock_pick_by_line = is_crossdock_pick_by_line;
    }

    public String getDefault_owner_nr() {
        return default_owner_nr;
    }

    public void setDefault_owner_nr(String default_owner_nr) {
        this.default_owner_nr = default_owner_nr;
    }

    public String getIs_code_req() {
        return is_code_req;
    }

    public void setIs_code_req(String is_code_req) {
        this.is_code_req = is_code_req;
    }

    public String getUnload_type() {
        return unload_type;
    }

    public void setUnload_type(String unload_type) {
        this.unload_type = unload_type;
    }
}
