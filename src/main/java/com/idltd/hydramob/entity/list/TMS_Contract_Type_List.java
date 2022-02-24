package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TMS_CONTRACTS_TYPE")
public class TMS_Contract_Type_List {
    @Id
    public Long tms_contt_id;

    @Column(name = "TMS_CONTT_SNAME", nullable = false)
    public String tms_contt_sname;
}