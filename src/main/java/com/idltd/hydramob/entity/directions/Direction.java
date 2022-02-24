package com.idltd.hydramob.entity.directions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Direction {
    @Id
    @Column(name = "DIR_ID", nullable = false)
    public Long dir_id;

    @Column(name = "DIR_SNAME", nullable = false)
    public String dir_sname;

    @Column(name = "DIR_NAME")
    public String dir_name;

    @Column(name = "DIR_DESCRIPTION")
    public String dir_description;
}
