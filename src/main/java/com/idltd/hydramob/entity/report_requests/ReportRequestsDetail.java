package com.idltd.hydramob.entity.report_requests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportRequestsDetail {
    @Id
    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "ALL_SUMM", nullable = false)
    public String all_summ;

    @Column(name = "SUCC_SUMM", nullable = false)
    public String succ_summ;

    @Column(name = "SUCC_COLOR", nullable = false)
    public String succ_color;

    @Column(name = "LOSE_SUMM", nullable = false)
    public String lose_summ;

    @Column(name = "LOSE_COLOR", nullable = false)
    public String lose_color;

    @Column(name = "CAN_SUMM", nullable = false)
    public String can_summ;

    @Column(name = "CAN_COLOR", nullable = false)
    public String can_color;

    @Column(name = "FAIL_SUMM", nullable = false)
    public String fail_summ;

    @Column(name = "FAIL_COLOR", nullable = false)
    public String fail_color;

    @Column(name = "NULL_SUM", nullable = false)
    public String null_sum;

    @Column(name = "NULL_COLOR", nullable = false)
    public String null_color;

    @Column(name = "NOT_FILL_SUM", nullable = false)
    public String not_fill_sum;

    @Column(name = "NOT_FILL_COLOR", nullable = false)
    public String not_fill_color;

    @Column(name = "NEW_SUM", nullable = false)
    public String new_sum;

    @Column(name = "NEW_COLOR", nullable = false)
    public String new_color;

    @Column(name = "WORK_SUM", nullable = false)
    public String work_sum;

    @Column(name = "WORK_COLOR", nullable = false)
    public String work_color;

    @Column(name = "COMPLETE_SUM", nullable = false)
    public String complete_sum;

    @Column(name = "COMPLETE_COLOR", nullable = false)
    public String complete_color;

    @Column(name = "SEND_SUM", nullable = false)
    public String send_sum;

    @Column(name = "SEND_COLOR", nullable = false)
    public String send_color;

    @Column(name = "USER_DEL_SUM", nullable = false)
    public String user_del_sum;

    @Column(name = "USER_DEL_COLOR", nullable = false)
    public String user_del_color;

    @Column(name = "ADMIN_DEL_SUM", nullable = false)
    public String admin_del_sum;

    @Column(name = "ADMIN_DEL_COLOR", nullable = false)
    public String admin_del_color;

    @Column(name = "CONTRACT_SUMM", nullable = false)
    public String contract_summ;

    @Column(name = "CONTRACT_COLOR", nullable = false)
    public String contract_color;

    @Column(name = "START_SUMM", nullable = false)
    public String start_summ;

    @Column(name = "START_COLOR", nullable = false)
    public String start_color;

    @Column(name = "CONTRACT_CAN_SUM", nullable = false)
    public String contract_can_sum;

    @Column(name = "CONTRACT_CAN_COLOR", nullable = false)
    public String contract_can_color;

    @Column(name = "START_CAN_SUM", nullable = false)
    public String start_can_sum;

    @Column(name = "START_CAN_COLOR", nullable = false)
    public String start_can_color;
}
