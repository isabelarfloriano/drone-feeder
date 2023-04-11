package com.dronefeeder.service;

import com.dronefeeder.exception.NotFoundException;
import com.dronefeeder.model.Video;
import com.dronefeeder.repository.DeliveryRepository;
import com.dronefeeder.repository.DroneRepository;
import com.dronefeeder.repository.VideoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VideoService class.
 */
@Service
public class VideoService {

  @Autowired
  VideoRepository videoRepository;

  @Autowired
  DeliveryRepository deliveryRepository;

  @Autowired
  DroneRepository droneRepository;

  /**
   * findAll method.
   */
  public List<Video> findAll() {
    return videoRepository.findAll();  
  }
  
  /**
   * findById method.
   */
  public Video findById(Long id) {
    return videoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));
  }
  
  /**
   * Create method.
   */
  public Video create(Video video) {
    droneRepository.findById(video.getDronefeeder().getId())
         .orElseThrow(() ->
             new NotFoundException("The video must be associated to an existing drone"));
    
    deliveryRepository.findById(video.getDelivery().getId())
        .orElseThrow(() ->
            new NotFoundException("The video must be associated to an existing delivery"));
    
    videoRepository.save(video);
 
    return video;
  }
  
  /**
   * Delete method.
   */
  public void delete(Long id) {
    Video video = videoRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));
    videoRepository.delete(video);
  }
  
  /**
   * Update method.
   */
  public Video update(Long id, Video video) {

    try {
      Video updatedVideo = videoRepository.findById(id).get();
      if (video.getUrl() != null) {
        updatedVideo.setUrl(video.getUrl());
      }

      if (video.getDelivery() != null) {
        updatedVideo.setDelivery(video.getDelivery());
      }

      if (video.getDronefeeder() != null) {
        updatedVideo.setDronefeeder(video.getDronefeeder());
      }

      videoRepository.save(updatedVideo);

      return updatedVideo;
    } catch (Exception error) {
      throw new NotFoundException("Matching object not found");
    }
  }

}
