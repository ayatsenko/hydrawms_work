package com.idltd.hydramob.entity.support_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSupportTaskResultTime {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "FACT_MONTH", nullable = false)
    public String fact_month;

    @Column(name = "DOC_FACT_TIME")
    public String doc_fact_time;

    @Column(name = "NEW_FACT_TIME")
    public String new_fact_time;

    @Column(name = "ERROR_FACT_TIME")
    public String error_fact_time;

    @Column(name = "SUPP_FACT_TIME")
    public String supp_fact_time;

    @Column(name = "ALL_FACT_TIME")
    public String all_fact_time;

    @Column(name = "RESULT_TIME")
    public String result_time;

    @Column(name = "RESULT_COLOR")
    public String result_color;

    @Column(name = "EMPTY_COLUMN")
    public String empty_column;
}
