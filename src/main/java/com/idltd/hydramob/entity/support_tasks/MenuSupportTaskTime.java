package com.idltd.hydramob.entity.support_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSupportTaskTime {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "TECH_COUNT", nullable = false)
    public Long tech_count;

    @Column(name = "TECH_PERSENT", nullable = false)
    public Long tech_persent;

    @Column(name = "TECH_COLOR", nullable = false)
    public String tech_color;


    @Column(name = "SUPP_COUNT", nullable = false)
    public Long supp_count;

    @Column(name = "SUPP_PERSENT", nullable = false)
    public Long supp_persent;

    @Column(name = "SUPP_COLOR", nullable = false)
    public String supp_color;


    @Column(name = "NEW_COUNT", nullable = false)
    public Long new_count;

    @Column(name = "NEW_PERSENT", nullable = false)
    public Long new_persent;

    @Column(name = "NEW_COLOR", nullable = false)
    public String new_color;

    @Column(name = "ALL_COUNT", nullable = false)
    public Long all_count;
}
