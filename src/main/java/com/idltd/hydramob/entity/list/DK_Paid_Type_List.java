package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DK_DETAIL_PAID_TYPE")
public class DK_Paid_Type_List {
    @Id
    @Column(name = "DK_PAID_TYPE_ID", nullable = false)
    public Long dk_paid_type_id;

    @Column(name = "DK_PAID_TYPE_NAME", nullable = false)
    public String dk_paid_type_name;

    @Column(name = "DK_PAID_TYPE_SNAME", nullable = false)
    public String dk_paid_type_sname;

    @Column(name = "DK_PAID_TYPE_DESCRIPTION", nullable = false)
    public String dk_paid_type_description;

    @Column(name = "DK_PAID_TYPE_COLOR", nullable = false)
    public String dk_paid_type_color;
}
