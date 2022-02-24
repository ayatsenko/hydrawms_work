package com.idltd.hydramob.entity.user_interface_context;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserInterfaceContext {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "MCURL_ID", nullable = false)
    public Long mcurl_id;

    @Column(name = "MC_ROW", nullable = false)
    public Long mc_row;

    @Column(name = "MC_ID", nullable = false)
    public Long mc_id;

    @Column(name = "MC_NAME", nullable = false)
    public String mc_name;

    @Column(name = "MC_DEFAULT", nullable = false)
    public Long mc_default;

    @Column(name = "MC_HIDE", nullable = false)
    public Long mc_hide;

    @Column(name = "MC_LIST_NUM", nullable = false)
    public Long mc_list_num;
}
