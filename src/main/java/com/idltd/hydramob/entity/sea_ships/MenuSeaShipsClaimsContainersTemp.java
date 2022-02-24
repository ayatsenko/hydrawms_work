package com.idltd.hydramob.entity.sea_ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSeaShipsClaimsContainersTemp {
    @Id
    @Column(name = "ROW_ID", nullable = false)
    public Long row_id;

    @Column(name = "CLM_ID")
    public Long clm_id;

    @Column(name = "CLM_CONTAINERS_ID")
    public Long clm_containers_id;

    @Column(name = "CLM_CONTAINERS_NAME")
    public String clm_containers_name;

    @Column(name = "CLM_CONTAINERS_NUM")
    public String clm_containers_num;

    @Column(name = "CLM_CONTAINERS_WEIGHT")
    public String clm_containers_weight;
}
