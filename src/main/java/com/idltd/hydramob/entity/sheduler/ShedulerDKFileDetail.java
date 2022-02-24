package com.idltd.hydramob.entity.sheduler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "SHEDULER_DK_DETAIL")
public class ShedulerDKFileDetail {
    @Id
    @Column(name = "SH_DK_ID", nullable = false)
    @SequenceGenerator(name = "SHEDULER_DK_DETAIL_SEQ", sequenceName = "SHEDULER_DK_DETAIL_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHEDULER_DK_DETAIL_SEQ")
    public Long sh_dk_id;

    @Column(name = "SFA_ID", nullable = false)
    public Long sfa_id;

    @JsonProperty(value="_00")
    @Column(name = "SH_DK_CURRENCY_NAME", nullable = false)
    public String sh_dk_currency_name;

    @JsonProperty(value="_01")
    @Column(name = "SH_DK_DEBT_START_DATE")
    public String sh_dk_debt_start_date;

    @JsonProperty(value="_02")
    @Column(name = "SH_DK_PAID_DATE")
    public String sh_dk_paid_date;

    @JsonProperty(value="_03")
    @Column(name = "SH_DK_ACT_INCOME_DATE")
    public String sh_dk_act_income_date;

    @JsonProperty(value="_04")
    @Column(name = "SH_DK_DOC_SUP_INCOME_DATE")
    public String sh_dk_doc_sup_income_date;

    @JsonProperty(value="_05")
    @Column(name = "SH_DK_DOC_RECIVED_DATE")
    public String sh_dk_doc_recived_date;

    @JsonProperty(value="_06")
    @Column(name = "SH_DK_BILL_DATE")
    public String sh_dk_bill_date;

    @JsonProperty(value="_07")
    @Column(name = "SH_DK_CONTRACT")
    public String sh_dk_contract;

    @JsonProperty(value="_08")
    @Column(name = "SH_DK_APPROVED_DEBT_SUM")
    public String sh_dk_approved_debt_sum;

    @JsonProperty(value="_09")
    @Column(name = "SH_DK_CONTRACT_ORIGINAL_CHECK")
    public String sh_dk_contract_original_check;

    @JsonProperty(value="_10")
    @Column(name = "SH_DK_ORDER")
    public String sh_dk_order;

    @JsonProperty(value="_11")
    @Column(name = "SH_DK_CNT_NAME")
    public String sh_dk_cnt_name;

    @JsonProperty(value="_12")
    @Column(name = "SH_DK_PAID_CHECK")
    public String sh_dk_paid_check;

    @JsonProperty(value="_13")
    @Column(name = "SH_DK_DEBT_MAX_DAY")
    public String sh_dk_debt_max_day;

    @JsonProperty(value="_14")
    @Column(name = "SH_DK_ACT_CHECK")
    public String sh_dk_act_check;

    @JsonProperty(value="_15")
    @Column(name = "SH_DK_DOC_SUP_HAVE_TO")
    public String sh_dk_doc_sup_have_to;

    @JsonProperty(value="_16")
    @Column(name = "SH_DK_COMPANY_NAME")
    public String sh_dk_company_name;

    @JsonProperty(value="_17")
    @Column(name = "SH_DK_DEBT_CALL_DAY")
    public String sh_dk_debt_call_day;

    @JsonProperty(value="_18")
    @Column(name = "SH_DK_DEBT_COUNT_DAY")
    public String sh_dk_debt_count_day;

    @JsonProperty(value="_19")
    @Column(name = "SH_DK_DEBT_DOC_DAY")
    public Long sh_dk_debt_doc_day;

    @JsonProperty(value="_20")
    @Column(name = "SH_DK_DEBT_SER_NAME")
    public String sh_dk_debt_ser_name;

    @JsonProperty(value="_21")
    @Column(name = "SH_DK_PREPAYING")
    public String sh_dk_prepaying;

    @JsonProperty(value="_22")
    @Column(name = "SH_DK_DEBT_SUM")
    public String sh_dk_debt_sum;

    @JsonProperty(value="_23")
    @Column(name = "SH_DK_SUM")
    public String sh_dk_sum;

    @JsonProperty(value="_24")
    @Column(name = "SH_DK_CURRENCY_SUM")
    public String SH_DK_CURRENCY_SUM;

    @JsonProperty(value="_25")
    @Column(name = "SH_DK_DOC_CURRENCY_SUM")
    public String SH_DK_DOC_CURRENCY_SUM;

    @JsonProperty(value="_26")
    @Column(name = "SH_DK_SYSTEM_ACCOUNT")
    public String sh_dk_system_account;

    @JsonProperty(value="_27")
    @Column(name = "SH_DK_PAID_TYPE")
    public String SH_DK_PAID_TYPE;

    @JsonProperty(value="_28")
    @Column(name = "SH_DK_PAID_PREMISS")
    public String sh_dk_paid_premiss;

    @JsonProperty(value="_29")
    @Column(name = "SH_DK_DEBT_DOC_MAX_DAY")
    public String sh_dk_debt_doc_max_day;

    @JsonProperty(value="_30")
    @Column(name = "SH_DK_CURRENCY_ID")
    public Long sh_dk_currency_id;

    @JsonProperty(value="AC_NUM_GUID")
    @Column(name = "SH_DK_SYSTEM_AUDIT_ACCOUNT")
    public String sh_dk_system_audit_account;

    @JsonProperty(value="CONTR_GUID")
    @Column(name = "SH_DK_DOC_GUID")
    public String sh_dk_doc_guid;

    @JsonProperty(value="ORDER_GUID")
    @Column(name = "SH_DK_ORDER_GUID")
    public String sh_dk_order_guid;

    @JsonProperty(value="COUNT_GUID")
    @Column(name = "SH_DK_CNT_GUID")
    public String sh_dk_cnt_guid;

    @JsonProperty(value="SUBDEVISION_GUID")
    @Column(name = "SH_DK_SER_GUID")
    public String sh_dk_ser_guid;
}
