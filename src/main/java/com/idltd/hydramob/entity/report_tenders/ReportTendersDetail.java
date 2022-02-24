package com.idltd.hydramob.entity.report_tenders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportTendersDetail {
    @Id
    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "ALL_SUMM", nullable = false)
    public Long all_summ;

    @Column(name = "ADMIN_DEL_SUMM", nullable = false)
    public Long admin_del_summ;

    @Column(name = "ADMIN_DEL_COLOR", nullable = false)
    public String admin_del_color;

    @Column(name = "USER_DEL_SUMM", nullable = false)
    public Long user_del_summ;

    @Column(name = "USER_DEL_COLOR", nullable = false)
    public String user_del_color;

    @Column(name = "NULL_SUMM", nullable = false)
    public Long null_summ;

    @Column(name = "NULL_COLOR", nullable = false)
    public String null_color;

    @Column(name = "NOT_FILL_SUMM", nullable = false)
    public Long not_fill_summ;

    @Column(name = "NOT_FILL_COLOR", nullable = false)
    public String not_fill_color;

    @Column(name = "NEW_SUMM", nullable = false)
    public Long new_summ;

    @Column(name = "NEW_COLOR", nullable = false)
    public String new_color;

    @Column(name = "WORK_SUMM", nullable = false)
    public Long work_summ;

    @Column(name = "WORK_COLOR", nullable = false)
    public String work_color;

    @Column(name = "COMPLETE_SUMM", nullable = false)
    public Long complete_summ;

    @Column(name = "COMPLETE_COLOR", nullable = false)
    public String complete_color;

    @Column(name = "SEND_SUMM", nullable = false)
    public Long send_summ;

    @Column(name = "SEND_COLOR", nullable = false)
    public String send_color;

    @Column(name = "CLOSE_SUMM", nullable = false)
    public Long close_summ;

    @Column(name = "CLOSE_NEW_SUMM", nullable = false)
    public Long close_new_summ;

    @Column(name = "CLOSE_NEW_COLOR", nullable = false)
    public String close_new_color;

    @Column(name = "CLOSE_SUC_SUMM", nullable = false)
    public Long close_suc_summ;

    @Column(name = "CLOSE_SUC_COLOR", nullable = false)
    public String close_suc_color;

    @Column(name = "CLOSE_LOSE_SUMM", nullable = false)
    public Long close_lose_summ;

    @Column(name = "CLOSE_LOSE_COLOR", nullable = false)
    public String close_lose_color;

    @Column(name = "CLOSE_CAN_SUMM", nullable = false)
    public Long close_can_summ;

    @Column(name = "CLOSE_CAN_COLOR", nullable = false)
    public String close_can_color;

    @Column(name = "CLOSE_FAIL_SUMM", nullable = false)
    public Long close_fail_summ;

    @Column(name = "CLOSE_FAIL_COLOR", nullable = false)
    public String close_fail_color;

    @Column(name = "CONTRACT_SUMM", nullable = false)
    public Long contract_summ;

    @Column(name = "CONTRACT_COLOR", nullable = false)
    public String contract_color;

    @Column(name = "START_SUMM", nullable = false)
    public Long start_summ;

    @Column(name = "START_COLOR", nullable = false)
    public String start_color;

    @Column(name = "CONTRACT_CAN_SUM", nullable = false)
    public Long contract_can_sum;

    @Column(name = "CONTRACT_CAN_COLOR", nullable = false)
    public String contract_can_color;

    @Column(name = "START_CAN_SUM", nullable = false)
    public Long start_can_sum;

    @Column(name = "START_CAN_COLOR", nullable = false)
    public String start_can_color;
}
