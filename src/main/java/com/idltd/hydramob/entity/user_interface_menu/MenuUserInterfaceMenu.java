package com.idltd.hydramob.entity.user_interface_menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserInterfaceMenu {
    @Id
    @Column(name = "MURL_ID", nullable = false)
    public Long murl_id;

    @Column(name = "MENU_ID", nullable = false)
    public Long menu_id;

    @Column(name = "MENU_NAME", nullable = false)
    public String menu_name;

    @Column(name = "MR_HIDE", nullable = false)
    public Long mr_hide;
}
