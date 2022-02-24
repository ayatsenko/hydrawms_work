package com.idltd.hydramob.entity.system_params;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSystemParams {
    @Id
    @Column(name = "CRM_SYS_PAR_ID", nullable = false)
    public Long crm_sys_par_id;

    @Column(name = "CRM_SYSTEM_ID", nullable = false)
    public Long crm_system_id;

    @Column(name = "CRM_SYSTEM_SNAME", nullable = false)
    public String crm_system_sname;

    @Column(name = "CRM_SYSTEM_SUB_ID", nullable = false)
    public Long crm_system_sub_id;

    @Column(name = "CRM_SYSTEM_SUB_SNAME", nullable = false)
    public String crm_system_sub_sname;

    @Column(name = "CRM_SYS_PAR_DEFAULT", nullable = false)
    public String crm_sys_par_default;

    @Column(name = "CRM_SYS_PAR_CLIENT", nullable = false)
    public String crm_sys_par_client;

    @Column(name = "CRM_SYS_PAR_DESCRIPTION", nullable = false)
    public String crm_sys_par_description;
}
