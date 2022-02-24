package com.idltd.hydramob.entity;

import javax.persistence.*;

@Entity
@Table(name = "SUPPLY_CONTENT")
public class Supply_content {
    @Id
    @SequenceGenerator(name = "SUPPLY_CONTENT_SEQ", sequenceName = "SUPPLY_CONTENT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLY_CONTENT_SEQ")
    public Long spc_id;

    @Column(name = "SP_ID", nullable = false)
    public Long sp_id;

    @Column(name = "SPC_SHIPMENT", nullable = false)
    public String spc_shipment;

    @Column(name = "SPC_TRACKING")
    public String spc_tracking;

    @Column(name = "SPC_RECEIVER")
    public String spc_receiver;

    @Column(name = "SPC_IMPORTER")
    public String spc_importer;

    @Column(name = "SPC_WEIGHT")
    public Float spc_weight;

    @Column(name = "SPC_VALUE")
    public Float spc_value;

    @Column(name = "SPC_CLIENT")
    public String spc_client;

    @Column(name = "SPC_CONTENT1")
    public String spc_content1;

    @Column(name = "SPC_CONTENT2")
    public String spc_content2;

    @Column(name = "SPC_CONTENT3")
    public String spc_content3;

    @Column(name = "SPC_CONTENT4")
    public String spc_content4;

    @Column(name = "SPC_CONTENT5")
    public String spc_content5;

    @Column(name = "SPC_CONTENT6")
    public String spc_content6;

    @Column(name = "spc_originhub")
    public String spc_originhub;

    @Column(name = "spc_shipper")
    public String spc_shipper;

    @Column(name = "spc_destinationhub")
    public String spc_destinationhub;

    @Column(name = "spc_comment")
    public String spc_comment;

    @Column(name = "spc_content_fact")
    public String spc_content_fact;

    @Column(name = "sf_id")
    public Long sf_id;
}