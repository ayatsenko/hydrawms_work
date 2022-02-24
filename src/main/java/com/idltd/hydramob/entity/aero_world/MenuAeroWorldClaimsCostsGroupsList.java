package com.idltd.hydramob.entity.aero_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuAeroWorldClaimsCostsGroupsList {
    @Id
    @Column(name = "CLMCGL_ID", nullable = false)
    public Long clmcgl_id;

    @Column(name = "CLMCG_TYPE_ID")
    public Long clmcg_type_id;

    @Column(name = "CLMCG_TYPE_NAME")
    public String clmcg_type_name;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "TRANS_COSTS_GROUP")
    public Long trans_costs_group;
}
