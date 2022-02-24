package com.idltd.hydramob.entity.auto_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHEDULER_DK_DETAIL")
public class MenuAutoDKFile {
        @Id
        @Column(name = "SH_DK_ID", nullable = false)
        public Long sh_dk_id;

        @Column(name = "SFA_ID", nullable = false)
        public Long sfa_id;

        @Column(name = "SH_DK_CURRENCY_NAME", nullable = false)
        public String sh_dk_currency_name;

        @Column(name = "SH_DK_DEBT_START_DATE")
        public String sh_dk_debt_start_date;

        @Column(name = "SH_DK_PAID_DATE")
        public String sh_dk_paid_date;

        @Column(name = "SH_DK_ACT_INCOME_DATE")
        public String sh_dk_act_income_date;

        @Column(name = "SH_DK_DOC_SUP_INCOME_DATE")
        public String sh_dk_doc_sup_income_date;

        @Column(name = "SH_DK_DOC_RECIVED_DATE")
        public String sh_dk_doc_recived_date;

        @Column(name = "SH_DK_BILL_DATE")
        public String sh_dk_bill_date;

        @Column(name = "SH_DK_CONTRACT")
        public String sh_dk_contract;

        @Column(name = "SH_DK_APPROVED_DEBT_SUM")
        public String sh_dk_approved_debt_sum;

        @Column(name = "SH_DK_CONTRACT_ORIGINAL_CHECK")
        public String sh_dk_contract_original_check;

        @Column(name = "SH_DK_ORDER")
        public String sh_dk_order;

        @Column(name = "SH_DK_CNT_NAME")
        public String sh_dk_cnt_name;

        @Column(name = "SH_DK_PAID_CHECK")
        public String sh_dk_paid_check;

        @Column(name = "SH_DK_DEBT_MAX_DAY")
        public String sh_dk_debt_max_day;

        @Column(name = "SH_DK_ACT_CHECK")
        public String sh_dk_act_check;

        @Column(name = "SH_DK_DOC_SUP_HAVE_TO")
        public String sh_dk_doc_sup_have_to;

        @Column(name = "SH_DK_COMPANY_NAME")
        public String sh_dk_company_name;

        @Column(name = "SH_DK_DEBT_CALL_DAY")
        public String sh_dk_debt_call_day;

        @Column(name = "SH_DK_DEBT_COUNT_DAY")
        public String sh_dk_debt_count_day;

        @Column(name = "SH_DK_DEBT_DOC_DAY")
        public Long sh_dk_debt_doc_day;

        @Column(name = "SH_DK_DEBT_SER_NAME")
        public String sh_dk_debt_ser_name;

        @Column(name = "SH_DK_PREPAYING")
        public String sh_dk_prepaying;

        @Column(name = "SH_DK_DEBT_SUM")
        public String sh_dk_debt_sum;

        @Column(name = "SH_DK_SUM")
        public String sh_dk_sum;

        @Column(name = "SH_DK_CURRENCY_SUM")
        public String sh_dk_currency_sum;

        @Column(name = "SH_DK_DOC_CURRENCY_SUM")
        public String sh_dk_doc_currency_sum;

        @Column(name = "SH_DK_SYSTEM_ACCOUNT")
        public String sh_dk_system_account;

        @Column(name = "SH_DK_PAID_TYPE")
        public String sh_dk_paid_type;

        @Column(name = "SH_DK_PAID_PREMISS")
        public String sh_dk_paid_premiss;

        @Column(name = "SH_DK_DEBT_DOC_MAX_DAY")
        public String sh_dk_debt_doc_max_day;

        @Column(name = "SH_DK_CURRENCY_ID")
        public Long sh_dk_currency_id;

        @Column(name = "SH_DK_SYSTEM_AUDIT_ACCOUNT")
        public String sh_dk_system_audit_account;

        @Column(name = "SH_DK_DOC_GUID")
        public String sh_dk_doc_guid;

        @Column(name = "SH_DK_ORDER_GUID")
        public String sh_dk_order_guid;

        @Column(name = "SH_DK_CNT_GUID")
        public String sh_dk_cnt_guid;

        @Column(name = "SH_DK_SER_GUID")
        public String sh_dk_ser_guid;
}
