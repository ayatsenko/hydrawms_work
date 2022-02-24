package com.idltd.hydramob.entity.positions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Positions {
    @Id
    @Column(name = "POS_ID", nullable = false)
    public Long pos_id;

    @Column(name = "POS_SNAME", nullable = false)
    public String pos_sname;

    @Column(name = "POS_NAME")
    public String pos_name;

    @Column(name = "POS_DESCRIPTION")
    public String pos_description;
}
