package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name = "SUPPLY_CONTENT_MODEL")
public class SupplyContentModel {


  @Id
  @Column(name = "SCM_ID", nullable = false)
  @SequenceGenerator(name = "SUPPLY_CONTENT_MODEL_SEQ", sequenceName = "SUPPLY_CONTENT_MODEL_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLY_CONTENT_MODEL_SEQ")
  private Long scm_id;

  @Column(name = "SP_ID", nullable = false)
  private Long sp_id;

  @Column(name = "SCM_NUM", nullable = false)
  private String scm_num;

  @Column(name = "SCM_BARCODE", nullable = false)
  private String scm_barcode;

  @Column(name = "SCM_NAME", nullable = false)
  private String scm_name;

    public Long getScm_id() {
        return scm_id;
    }

    public void setScm_id(Long scm_id) {
        this.scm_id = scm_id;
    }

    public Long getSp_id() {
        return sp_id;
    }

    public void setSp_id(Long sp_id) {
        this.sp_id = sp_id;
    }

    public String getScm_num() {
        return scm_num;
    }

    public void setScm_num(String scm_num) {
        this.scm_num = scm_num;
    }

    public String getScm_barcode() {
        return scm_barcode;
    }

    public void setScm_barcode(String scm_barcode) {
        this.scm_barcode = scm_barcode;
    }

    public String getScm_name() {
        return scm_name;
    }

    public void setScm_name(String scm_name) {
        this.scm_name = scm_name;
    }
}
