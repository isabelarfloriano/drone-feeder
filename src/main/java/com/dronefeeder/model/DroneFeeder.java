package com.dronefeeder.model;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DroneFeeder class.
 */
@Entity
@Table(name = "tb_dronefeeder")
public class DroneFeeder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String brand;
  private String modelName;
  private String serialNumber;

  @OneToMany(mappedBy = "dronefeeder", cascade = CascadeType.ALL, orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<Delivery> deliveries = new ArrayList<Delivery>();

  //@OneToMany(mappedBy = "dronefeeder", cascade = CascadeType.ALL, orphanRemoval = true)
  //private List<Videos> videos;

  public DroneFeeder() {}
  
  /**
   * DroneFeeder constructor.
   */
  public DroneFeeder(String brand, String modelName, String serialNumber) {
    this.brand = brand;
    this.modelName = modelName;
    this.serialNumber = serialNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }
  
  public List<Delivery> getDeliveries() {
    return deliveries;
  }

  public void addDelivery(Delivery delivery) {
    this.deliveries.add(delivery);
  }


}
