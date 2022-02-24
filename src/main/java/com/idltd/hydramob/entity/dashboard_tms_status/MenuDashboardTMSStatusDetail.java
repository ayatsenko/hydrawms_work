package com.idltd.hydramob.entity.dashboard_tms_status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuDashboardTMSStatusDetail {
    @Id
    @Column(name = "USER_ID", nullable = false)
    public Long user_id;

    @Column(name = "USER_NAME", nullable = false)
    public String user_name;

    @Column(name = "ALL_COUNT")
    public Long all_count;

    @Column(name = "NOTFILLED_COUNT")
    public Long notfilled_count;

    @Column(name = "NOTFILLED_COLOR")
    public String notfilled_color;

    @Column(name = "NEW_COUNT")
    public Long new_count;

    @Column(name = "NEW_COLOR")
    public String new_color;

    @Column(name = "DRAWUP_COUNT")
    public Long drawup_count;

    @Column(name = "DRAWUP_COLOR")
    public String drawup_color;

    @Column(name = "ONLOAD_COUNT")
    public Long onload_count;

    @Column(name = "ONLOAD_COLOR")
    public String onload_color;

    @Column(name = "LOADED_COUNT")
    public Long loaded_count;

    @Column(name = "LOADED_COLOR")
    public String loaded_color;

    @Column(name = "DOCUMENT_COUNT")
    public Long document_count;

    @Column(name = "DOCUMENT_COLOR")
    public String document_color;

    @Column(name = "BORDER_COUNT")
    public Long border_count;

    @Column(name = "BORDER_COLOR")
    public String border_color;

    @Column(name = "CUSTOM_COUNT")
    public Long custom_count;

    @Column(name = "CUSTOM_COLOR")
    public String custom_color;

    @Column(name = "UNLOADING_COUNT")
    public Long unloading_count;

    @Column(name = "UNLOADING_COLOR")
    public String unloading_color;

    @Column(name = "UNLOADED_COUNT")
    public Long unloaded_count;

    @Column(name = "UNLOADED_COLOR")
    public String unloaded_color;

    @Column(name = "CLOSED_COUNT")
    public Long closed_count;

    @Column(name = "CLOSED_COLOR")
    public String closed_color;

    @Column(name = "PAYED_COUNT")
    public Long payed_count;

    @Column(name = "PAYED_COLOR")
    public String payed_color;
}
