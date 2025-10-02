package com.ltw.bt06.repository;

import com.ltw.bt06.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByTitleContainingIgnoreCase(String title);
}
