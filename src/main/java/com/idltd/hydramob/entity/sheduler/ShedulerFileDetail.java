package com.idltd.hydramob.entity.sheduler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.idltd.hydramob.utils.FlexibleFloatDeserializer;

import javax.persistence.*;

@Entity
@Table(name = "SHEDULER_FINANCE_DETAIL")
public class ShedulerFileDetail {
    @Id
    @Column(name = "SFAD_ID", nullable = false)
    @SequenceGenerator(name = "SHEDULER_FINANCE_DETAIL_SEQ", sequenceName = "SHEDULER_FINANCE_DETAIL_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHEDULER_FINANCE_DETAIL_SEQ")
    public Long sfad_id;

    @Column(name = "SFA_ID", nullable = false)
    public Long sfa_id;

    @JsonProperty(value="FA_DOC")
    @Column(name = "FA_DOC", nullable = false)
    public String fa_doc;

    @JsonProperty(value="FA_DATE")
    @Column(name = "FA_DATE", nullable = false)
    public String fa_date;

    @JsonProperty(value="FA_WAY")
    @Column(name = "FA_WAY")
    public String fa_way;

    @JsonProperty(value="CNT_NAME")
    @Column(name = "CNT_NAME")
    public String cnt_name;

    @JsonProperty(value="CNT_IDENTIFYCODE")
    @Column(name = "CNT_IDENTIFYCODE")
    public String cnt_identifycode;

    @JsonProperty(value="USERNAME")
    @Column(name = "USERNAME")
    public String username;

    @JsonProperty(value="FA_DIVISION")
    @Column(name = "FA_DIVISION")
    public String fa_division;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    @JsonProperty(value="SUMM")
    @Column(name = "SUMM", nullable = false)
    public Float summ;

    @JsonProperty(value="FA_Link")
    @Column(name = "FA_LINK")
    public String fa_link;

    @JsonProperty(value="FA_BrLink")
    @Column(name = "FA_BRLINK")
    public String fa_brlink;

    @JsonProperty(value="FA_ССLink")
    @Column(name = "FA_CCLINK")
    public String fa_cclink;

    @JsonProperty(value="FA_USLink")
    @Column(name = "FA_USLINK")
    public String fa_uslink;

    @JsonProperty(value="FA_GUID")
    @Column(name = "FA_GUID")
    public String fa_guid;

    @JsonProperty(value="BR_GUID")
    @Column(name = "BR_GUID")
    public String br_guid;

    @JsonProperty(value="CC_GUID")
    @Column(name = "CC_GUID")
    public String cc_guid;

    @JsonProperty(value="US_GUID")
    @Column(name = "US_GUID")
    public String us_guid;

    @JsonProperty(value="ID_DELETE")
    @Column(name = "ID_DELETE")
    public String id_delete;

    @JsonProperty(value="DATA_PAY")
    @Column(name = "DATA_PAY")
    public String data_pay;

    @JsonProperty(value="Cur_PAY")
    @Column(name = "CUR_PAY")
    public String cur_pay;

    @JsonProperty(value="Summ_PAY")
    @Column(name = "SUMM_PAY")
    public String summ_pay;

    @JsonProperty(value="SumCur_PAY")
    @Column(name = "SUMCUR_PAY")
    public String sumcur_pay;

    @JsonProperty(value="Customer_order")
    @Column(name = "CUSTOMER_ORDER")
    public String customer_order;

    @JsonProperty(value="Customer_order_GUID")
    @Column(name = "CUSTOMER_ORDER_GUID")
    public String customer_order_guid;
}
