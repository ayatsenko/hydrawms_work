package com.idltd.hydramob.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SUPPLY")
public class Supply {
    @Id
    @SequenceGenerator(name = "SUPPLY_SEQ", sequenceName = "SUPPLY_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUPPLY_SEQ")
    public Long sp_id;

    @Column(name = "SP_FLIGHT", nullable = false)
    public String sp_flight;

    @Column(name = "SP_SHIPPER", nullable = false)
    public String sp_shipper;

    @Column(name = "SP_COMMENT", nullable = false)
    public String sp_comment;

    @Column(name = "SP_ORIGIN_HUB", nullable = false)
    public String sp_origin_hub;

    @Column(name = "SP_DESTINATION_HUB", nullable = false)
    public String sp_destination_hub;

    @Column(name = "SP_ARRIVAL")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    @Temporal(TemporalType.DATE)
    public Date sp_arrival;
}