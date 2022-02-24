package com.idltd.hydramob.entity.full_world;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuClaimReportParamsList {
    @Id
    @Column(name = "CLM_REP_LINK_ID", nullable = false)
    public Long clm_rep_link_id;

    @Column(name = "REP_PARAM_ID", nullable = false)
    public Long rep_param_id;

    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "REP_PARAM_NAME")
    public String rep_param_name;

    @Column(name = "REP_PARAM_VALUE")
    public String rep_param_value;

    @Column(name = "CLM_REP_LINK_EDIT")
    public Long clm_rep_link_edit;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
