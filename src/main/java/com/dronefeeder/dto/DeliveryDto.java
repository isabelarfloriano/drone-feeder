package com.dronefeeder.dto;

/**
 * DeliveryDto class.
 */
public class DeliveryDto {
  private String latitude;
  private String longitude;
  private Long dronefeeder;
  private Long video;
  private String deliveryDateAndTime;
  private String orderDateAndTime;
  
  /**
   * DeliveryDto constructor.
   */
  public DeliveryDto(
      String latitude, String longitude, Long dronefeeder, Long video,
      String deliveryDateAndTime, String orderDateAndTime) {
    this.setLatitude(latitude);
    this.setLongitude(longitude);
    this.setDronefeeder(dronefeeder);
    this.setVideo(video);
    this.setDeliveryDateAndTime(deliveryDateAndTime);
    this.setOrderDateAndTime(orderDateAndTime);
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

  public Long getDronefeeder() {
    return dronefeeder;
  }

  public void setDronefeeder(Long dronefeeder) {
    this.dronefeeder = dronefeeder;
  }

  public Long getVideo() {
    return video;
  }

  public void setVideo(Long video) {
    this.video = video;
  }

  public String getDeliveryDateAndTime() {
    return deliveryDateAndTime;
  }

  public void setDeliveryDateAndTime(String deliveryDateAndTime) {
    this.deliveryDateAndTime = deliveryDateAndTime;
  }

  public String getOrderDateAndTime() {
    return orderDateAndTime;
  }

  public void setOrderDateAndTime(String orderDateAndTime) {
    this.orderDateAndTime = orderDateAndTime;
  }

}
