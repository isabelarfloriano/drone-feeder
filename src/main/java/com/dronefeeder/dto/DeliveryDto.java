package com.dronefeeder.dto;

/**
 * DeliveryDto class.
 */
public class DeliveryDto {
  private String latitude;
  private String longitude;
  private Long dronefeederId;
  private Long videoId;
  private String deliveryDateAndTime;
  private String orderDateAndTime;
  
  /**
   * DeliveryDto constructor.
   */
  public DeliveryDto(
      String latitude, String longitude, Long dronefeederId, Long videoId,
      String deliveryDateAndTime, String orderDateAndTime) {
    this.setLatitude(latitude);
    this.setLongitude(longitude);
    this.setDronefeederId(dronefeederId);
    this.setVideoId(videoId);
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

  public Long getDronefeederId() {
    return dronefeederId;
  }

  public void setDronefeederId(Long dronefeederId) {
    this.dronefeederId = dronefeederId;
  }

  public Long getVideoId() {
    return videoId;
  }

  public void setVideoId(Long videoId) {
    this.videoId = videoId;
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
