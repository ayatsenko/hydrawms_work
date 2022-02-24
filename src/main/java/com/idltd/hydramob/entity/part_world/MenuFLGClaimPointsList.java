package com.idltd.hydramob.entity.part_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFLGClaimPointsList {
    @Id
    @Column(name = "CLM_POINT_ID", nullable = false)
    public Long clm_point_id;

    @Column(name = "USER_ID")
    public Long user_id;

    @Column(name = "USER_NAME")
    public String user_name;

    @Column(name = "CLM_POINT_ADD_DATE")
    public String clm_point_add_date;

    @Column(name = "CLM_POINT_DATE")
    public String clm_point_date;

    @Column(name = "CLM_POINT_NAME")
    public String clm_point_name;
}
