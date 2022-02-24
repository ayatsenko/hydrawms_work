package com.idltd.hydramob.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuUserSystemList {
    @Id
    @Column(name = "USRSL_ID", nullable = false)
    public Long usrsl_id;

    @Column(name = "USRSL_NAME")
    public String usrsl_name;

    @Column(name = "USRSL_DATE")
    public String usrsl_date;
}
