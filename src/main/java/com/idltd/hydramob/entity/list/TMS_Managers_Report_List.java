package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TMS_Managers_Report_List {
    @Id
    public Long id;

    @Column(name = "USERNAME", nullable = false)
    public String username;

    @Column(name = "USER_SURNAME", nullable = false)
    public String user_surname;
}