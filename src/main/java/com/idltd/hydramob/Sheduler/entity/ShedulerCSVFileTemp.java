package com.idltd.hydramob.Sheduler.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "EXCHANGE_SERVICE_TYPE_FILE_TEMP", schema = "HCOMM")
public class ShedulerCSVFileTemp {

    @Column(name = "CNT_ID", nullable = false)
    public Long cnt_id;

    @Column(name = "EXCH_SER_FILE_ID", nullable = false)
    public Long exch_ser_file_id;

    @Id
    @Column(name = "EXCH_SER_FILE_ROW_ID", nullable = false)
    @SequenceGenerator(name = "EXCHANGE_SERVICE_TYPE_FIL3_SEQ", sequenceName = "EXCHANGE_SERVICE_TYPE_FIL3_SEQ", schema = "HCOMM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_SERVICE_TYPE_FIL3_SEQ")
    public Long exch_ser_file_row_id;

    @JsonProperty(value="id")
    @Column(name = "COLUMN_1")
    public String column_1;

    @JsonProperty(value="Date")
    @Column(name = "COLUMN_2")
    public String column_2;

    @JsonProperty(value="Order No.")
    @Column(name = "COLUMN_3")
    public String column_3;

    @JsonProperty(value="Item ID")
    @Column(name = "COLUMN_4")
    public String column_4;

    @JsonProperty(value="Item title")
    @Column(name = "COLUMN_5")
    public String column_5;

    @JsonProperty(value="SKU")
    @Column(name = "COLUMN_6")
    public String column_6;

    @JsonProperty(value="Qty")
    @Column(name = "COLUMN_7")
    public String column_7;

    @JsonProperty(value="Shipping Method")
    @Column(name = "COLUMN_8")
    public String column_8;

    @JsonProperty(value="Special Instructions")
    @Column(name = "COLUMN_9")
    public String column_9;

    @JsonProperty(value="Name")
    @Column(name = "COLUMN_10")
    public String column_10;

    @JsonProperty(value="C'nee tel number")
    @Column(name = "COLUMN_11")
    public String column_11;

    @JsonProperty(value="Email")
    @Column(name = "COLUMN_12")
    public String column_12;

    @JsonProperty(value="Address Line 1")
    @Column(name = "COLUMN_13")
    public String column_13;

    @JsonProperty(value="Address Line 2")
    @Column(name = "COLUMN_14")
    public String column_14;

    @JsonProperty(value="City")
    @Column(name = "COLUMN_15")
    public String column_15;

    @JsonProperty(value="State")
    @Column(name = "COLUMN_16")
    public String column_16;

    @JsonProperty(value="Postcode")
    @Column(name = "COLUMN_17")
    public String column_17;

    @JsonProperty(value="Country")
    @Column(name = "COLUMN_18")
    public String column_18;
}
