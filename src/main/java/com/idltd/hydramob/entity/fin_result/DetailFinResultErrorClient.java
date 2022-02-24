package com.idltd.hydramob.entity.fin_result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailFinResultErrorClient {
    @Id
    @Column(name = "SFAD_ID", nullable = false)
    public Long sfad_id;

    @Column(name = "CNT_NAME", nullable = false)
    public String cnt_name;

    @Column(name = "CNT_IDENTIFYCODE")
    public String cnt_identifycode;

    @Column(name = "CC_GUID")
    public String cc_guid;

    @Column(name = "USER_ID")
    public Long user_id;
}
