package com.idltd.hydramob.entity.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_PAYMENTS_TYPE")
public class ProjectPaymentTypeList {
    @Id
    public Long pr_pay_type_id;

    @Column(name = "PR_PAY_TYPE_NAME", nullable = false)
    public String pr_pay_type_name;

    @Column(name = "PR_PAY_TYPE_SNAME", nullable = false)
    public String pr_pay_type_sname;

    @Column(name = "PR_PAY_TYPE_DESCRIPTION", nullable = false)
    public String pr_pay_type_description;

    @Column(name = "PR_PAY_TYPE_COLOUR", nullable = false)
    public String pr_pay_type_colour;
}