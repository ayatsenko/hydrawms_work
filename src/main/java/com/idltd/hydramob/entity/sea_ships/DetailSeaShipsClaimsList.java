package com.idltd.hydramob.entity.sea_ships;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DetailSeaShipsClaimsList {
    @Id
    @Column(name = "CLM_ID", nullable = false)
    public Long clm_id;

    @Column(name = "CLM_NUM")
    public String clm_num;

    @Column(name = "CLM_CLIENT_NUM")
    public String clm_client_num;

    @Column(name = "CNT_ID")
    public Long cnt_id;

    @Column(name = "CNT_NAME")
    public String cnt_name;

    @Column(name = "SER_ID")
    public Long ser_id;

    @Column(name = "CLM_INCOTERMS_ID")
    public Long clm_incoterms_id;

    @Column(name = "CLM_BULK_TYPE_ID")
    public Long clm_bulk_type_id;

    @Column(name = "SEA_AGENT_ID")
    public Long sea_agent_id;

    @Column(name = "CLM_DOC_CHECK")
    public Long clm_doc_check;

    @Column(name = "CLM_SEA_LINE_ID")
    public Long clm_sea_line_id;

    @Column(name = "SHIP_ID")
    public Long ship_id;

    @Column(name = "START_COUNTRY_ID")
    public Long start_country_id;

    @Column(name = "START_SHIPYARD_ID")
    public Long start_shipyard_id;

    @Column(name = "END_COUNTRY_ID")
    public Long end_country_id;

    @Column(name = "END_SHIPYARD_ID")
    public Long end_shipyard_id;

    @Column(name = "CLM_LOAD_DATE")
    public String clm_load_date;

    @Column(name = "CLM_ETA_DATE")
    public String clm_eta_date;

    @Column(name = "CLM_ATA_DATE")
    public String clm_ata_date;

    @Column(name = "CLM_MHB")
    public String clm_mhb;

    @Column(name = "CLM_HBL")
    public String clm_hbl;

    @Column(name = "SHIPYARD_DATE")
    public String shipyard_date;

    @Column(name = "BORDER_DATE")
    public String border_date;

    @Column(name = "TRANS_CNT_ID")
    public Long trans_cnt_id;

    @Column(name = "CNTC_ID")
    public Long cntc_id;

    @Column(name = "CNTT_ID")
    public Long cntt_id;

    @Column(name = "CNTD_ID")
    public Long cntd_id;

    @Column(name = "CLM_CONTAINERS_ID")
    public Long clm_containers_id;

    @Column(name = "CLM_CONTAINERS_NUM")
    public String clm_containers_num;

    @Column(name = "CLM_CONTAINERS_WEIGHT")
    public String clm_containers_weight;

    @Column(name = "CLM_SUM")
    public String clm_sum;

    @Column(name = "CLM_SUM_CURRENCY_ID")
    public Long clm_sum_currency_id;
}
