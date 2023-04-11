package com.dronefeeder.controller;


import com.dronefeeder.model.Video;
import com.dronefeeder.service.VideoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VideoController class.
 */
@RestController
@RequestMapping("/dronefeeder/video")
public class VideoController {

  @Autowired
  VideoService videoService;

  @GetMapping
  public List<Video> findAll() {
    return videoService.findAll();
  }

  @GetMapping("/{id}")
  public Video findById(@PathVariable Long id) {
    return videoService.findById(id);
  }

  @PostMapping
  public Video create(@RequestBody Video video) {
    return videoService.create(video);
  }

  @PutMapping("/{id}")
  public Video update(@RequestBody Video video, @PathVariable Long id) {
    return videoService.update(id, video);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    videoService.delete(id);
  }
}
