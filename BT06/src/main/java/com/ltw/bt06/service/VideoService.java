package com.ltw.bt06.service;

import com.ltw.bt06.model.Video;
import com.ltw.bt06.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private final VideoRepository repo;

    public VideoService(VideoRepository repo) {
        this.repo = repo;
    }

    public List<Video> getAll() {
        return repo.findAll();
    }

    public Video getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Video save(Video video) {
        return repo.save(video);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Video> search(String keyword) {
        return repo.findByTitleContainingIgnoreCase(keyword);
    }
}
