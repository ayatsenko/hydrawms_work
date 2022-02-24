package com.idltd.hydramob.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DISPATCH")
public class Dispatch {

  @Id
  @Column(name = "DIS_ID", nullable = false)
  @SequenceGenerator(name = "DISPATCH_SEQ", sequenceName = "DISPATCH_SEQ", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISPATCH_SEQ")
  public Long dis_id;

  @Column(name = "DIS_NUM")
  public String dis_num;

  @Column(name = "REQ_ID")
  public Long req_id;

  @Column(name = "AWB_ID")
  public Long awb_id;

  @Column(name = "DIS_SEATSNUM")
  public Long dis_seatsnum;

  @Column(name = "DIS_AGENT_ARRIVAL_DATE")
  @Temporal(TemporalType.DATE)
  public Date dis_agent_arrival_date;

  @Column(name = "DIS_AIRPORT_ARRIVAL_DATE")
  @Temporal(TemporalType.DATE)
  public Date dis_airport_arrival_date;

  @Column(name = "DIS_TRANSIT_AIRPORT_DATE")
  @Temporal(TemporalType.DATE)
  public Date dis_transit_airport_date;

  @Column(name = "DIS_DEPARTURE_AIRPORT_DATE_F")
  @Temporal(TemporalType.DATE)
  public Date dis_departure_airport_date_f;

  @Column(name = "DIS_ARRIVAL_DATE_KIEV_F")
  @Temporal(TemporalType.DATE)
  public Date dis_arrival_date_kiev_f;

  @Column(name = "DIS_WEIGHT_F")
  public Double dis_weight_f;

  @Column(name = "DIS_VOLUME_WEIGHT_F")
  public Double dis_volume_weight_f;

  @Column(name = "DIS_CSS_DATE")
  @Temporal(TemporalType.DATE)
  public Date dis_css_date;

  @Column(name = "ROUTE_ID")
  public Long route_id;

  @Column(name = "DIS_ARRIVAL_DATE_KIEV_P")
  @Temporal(TemporalType.DATE)
  public Date dis_arrival_date_kiev_p;
}
