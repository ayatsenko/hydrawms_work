package com.idltd.hydramob.entity.fin_result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuFinResultDetail {
    @Id
    @Column(name = "SER_ID", nullable = false)
    public Long ser_id;

    @Column(name = "SER_NAME", nullable = false)
    public String ser_name;

    @Column(name = "FIN_COUNT")
    public String fin_count;

    @Column(name = "FIN_RESULT")
    public String fin_result;

    @Column(name = "ERROR_COUNT")
    public String error_count;

    @Column(name = "ERROR_RESULT")
    public String error_result;

    @Column(name = "TOTAL_COUNT")
    public String total_count;

    @Column(name = "TOTAL_RESULT")
    public String total_result;
}
