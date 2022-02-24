package com.idltd.hydramob.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.idltd.hydramob.utils.FlexibleFloatDeserializer;

import javax.persistence.*;

@Entity
@Table(name = "SUPPLY_CONTENT")
public class SupplyContent_json {
    @Id
    @SequenceGenerator(name = "SUPPLY_CONTENT_SEQ", sequenceName = "SUPPLY_CONTENT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLY_CONTENT_SEQ")
    public Long spc_id;

    @Column(name = "SP_ID", nullable = false)
    public Long sp_id;

    @JsonProperty(value="Shipment")
    @Column(name = "SPC_SHIPMENT", nullable = false)
    public String spc_shipment;

    @JsonProperty(value="Tracking")
    @Column(name = "SPC_TRACKING")
    public String spc_tracking;

    @JsonProperty(value="Receiver")
    @Column(name = "SPC_RECEIVER")
    public String spc_receiver;

    @JsonProperty(value="Importer")
    @Column(name = "SPC_IMPORTER")
    public String spc_importer;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    @JsonProperty(value="Weight (kg)")
    @Column(name = "SPC_WEIGHT")
    public Float spc_weight;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    @JsonProperty(value="Value (USD)")
    @Column(name = "SPC_VALUE")
    public Float spc_value;

    @JsonProperty(value="Client")
    @Column(name = "SPC_CLIENT")
    public String spc_client;

    @JsonProperty(value="Content1")
    @Column(name = "SPC_CONTENT1")
    public String spc_content1;

    @JsonProperty(value="Content2")
    @Column(name = "SPC_CONTENT2")
    public String spc_content2;

    @JsonProperty(value="Content3")
    @Column(name = "SPC_CONTENT3")
    public String spc_content3;

    @JsonProperty(value="Content4")
    @Column(name = "SPC_CONTENT4")
    public String spc_content4;

    @JsonProperty(value="Content5")
    @Column(name = "SPC_CONTENT5")
    public String spc_content5;

    @JsonProperty(value="Content6")
    @Column(name = "SPC_CONTENT6")
    public String spc_content6;

    @JsonProperty(value="Origin HUB")
    @Column(name = "spc_originhub")
    public String spc_OriginHUB;

    @JsonProperty(value="Shipper")
    @Column(name = "spc_shipper")
    public String spc_shipper;

    @JsonProperty(value="Destination HUB")
    @Column(name = "spc_destinationhub")
    public String spc_destinationhub;

    @JsonProperty(value="Comment")
    @Column(name = "spc_comment")
    public String spc_comment;

    @Column(name = "sf_id")
    public Long sf_id;
}
