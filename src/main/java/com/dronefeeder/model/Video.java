package com.dronefeeder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Video class.
 */
@Entity
@Table(name = "tb_video")
public class Video {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String url;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "dronefeeder_id")
  private DroneFeeder dronefeeder;
  
  @JsonIgnore
  @OneToOne(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true,
      fetch = FetchType.LAZY)
  private Delivery delivery;
  
  public Video() {}
  
  public Video(String url, DroneFeeder dronefeeder) {
    this.url = url;
    this.dronefeeder = dronefeeder;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  public DroneFeeder getDronefeeder() {
    return dronefeeder;
  }

  public void setDronefeeder(DroneFeeder dronefeeder) {
    this.dronefeeder = dronefeeder;
  }
  
  public Delivery getDelivery() {
    return delivery;
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
  }
}
