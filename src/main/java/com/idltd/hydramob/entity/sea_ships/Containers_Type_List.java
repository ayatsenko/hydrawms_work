package com.idltd.hydramob.entity.sea_ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CONTAINERS_TYPE")
public class Containers_Type_List {
    @Id
    @Column(name = "CLM_CONTAINERS_ID", nullable = false)
    public Long clm_containers_id;

    @Column(name = "CLM_CONTAINERS_NAME", nullable = false)
    public String clm_containers_name;

    @Column(name = "CLM_CONTAINERS_SNAME", nullable = false)
    public String clm_containers_sname;

    @Column(name = "CLM_CONTAINERS_DESCRIPTION", nullable = false)
    public String clm_containers_description;

    @Column(name = "CLM_CONTAINERS_COLOR", nullable = false)
    public String clm_containers_color;
}