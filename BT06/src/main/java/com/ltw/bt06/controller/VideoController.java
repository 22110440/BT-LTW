package com.ltw.bt06.controller;

import com.ltw.bt06.model.Video;
import com.ltw.bt06.service.VideoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/videos")
public class VideoController {

    private final VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Video> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Video getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Video create(@RequestBody Video video) {
        return service.save(video);
    }

    @PutMapping("/{id}")
    public Video update(@PathVariable Long id, @RequestBody Video video) {
        video.setId(id);
        return service.save(video);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<Video> search(@RequestParam String keyword) {
        return service.search(keyword);
    }
}
