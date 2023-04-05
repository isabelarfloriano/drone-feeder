package com.dronefeeder.model;

import java.util.Date;
import javax.persistence.Entity;
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

  private boolean deliveryStatus;

  private Date currentDateAndTime;

  private Date deliveryDateAndTime;
  
  @ManyToOne
  @JoinColumn(name = "dronefeeder_id")
  private DroneFeeder dronefeeder;
  
  //  @OneToOne
  //  @JoinColumn(name = "video_id")
  //  private Video video;
  
  /**
   * Delivery constructor.
   */
  public Delivery() {
    this.currentDateAndTime = new Date();
    this.deliveryStatus = false;
    this.deliveryDateAndTime = null;
  }
  
  public Delivery(String latitude, String longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  //  /**
  //   * Delivery constructor.
  //   */
  //  public Delivery(String latitude, String longitude, DroneFeeder dronefeeder, Video video) {
  //    this.latitude = latitude;
  //    this.longitude = longitude;
  //    this.dronefeeder = dronefeeder;
  //    this.video = video;
  //    this.currentDateAndTime = new Date();
  //    this.deliveryStatus = false;
  //    this.deliveryDateAndTime = null;
  //  }

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

  public boolean isDeliveryStatus() {
    return deliveryStatus;
  }

  public void setDeliveryStatus(boolean deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public Date getCurrentDateAndTime() {
    return currentDateAndTime;
  }

  public void setCurrentDateAndTime(Date currentDateAndTime) {
    this.currentDateAndTime = currentDateAndTime;
  }

  public Date getDeliveryDateAndTime() {
    return deliveryDateAndTime;
  }

  public void setDeliveryDateAndTime(Date deliveryDateAndTime) {
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
