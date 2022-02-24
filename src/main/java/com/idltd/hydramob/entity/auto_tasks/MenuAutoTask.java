package com.idltd.hydramob.entity.auto_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuAutoTask {
    @Id
    @Column(name = "SE_ID", nullable = false)
    public Long se_id;

    @Column(name = "SET_ID", nullable = false)
    public Long set_id;

    @Column(name = "SET_SNAME")
    public String set_sname;

    @Column(name = "SE_CREATE")
    public String se_create;

    @Column(name = "SE_STATE")
    public String se_state;

    @Column(name = "SE_STATE_COLOR")
    public String se_state_color;

    @Column(name = "SE_FK_ID")
    public Long se_fk_id;

    @Column(name = "SE_ERROR")
    public String se_error;
}
