package com.dronefeeder.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.persistence.OneToOne;

/**
 * Delivery class.
 */
@Entity
@Table(name = "tb_delivery")
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String latitude;
  
  private String longitude;

  private String orderDateAndTime;

  private String deliveryDateAndTime;
  
  private String deliveryStatus;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "dronefeeder_id")
  private DroneFeeder dronefeeder;
  
  //    @OneToOne
  //    @JoinColumn(name = "video_id")
  //    private Video video;

  public Delivery() {}

  public Delivery( String latitude, String longitude, String orderDateAndTime, String deliveryDateAndTime,
      String deliveryStatus, DroneFeeder dronefeeder) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.orderDateAndTime = orderDateAndTime;
    this.deliveryDateAndTime = deliveryDateAndTime;
    this.deliveryStatus = deliveryStatus;
    this.dronefeeder = dronefeeder;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getDeliveryStatus() {
    return deliveryStatus;
  }

  public void setDeliveryStatus(String deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public String getOrderDateAndTime() {
    return orderDateAndTime;
  }

  public void setOderDateAndTime(String orderDateAndTime) {
    this.orderDateAndTime = orderDateAndTime;
  }

  public String getDeliveryDateAndTime() {
    return deliveryDateAndTime;
  }

  public void setDeliveryDateAndTime(String deliveryDateAndTime) {
    this.deliveryDateAndTime = deliveryDateAndTime;
  }

  public DroneFeeder getDronefeeder() {
    return dronefeeder;
  }

  public void setDronefeeder(DroneFeeder dronefeeder) {
    this.dronefeeder = dronefeeder;
  }

  //  public Video getVideo() {
  //    return video;
  //  }
  //
  //  public void setVideo(Video video) {
  //    this.video = video;
  //  }
}
