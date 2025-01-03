package main.repository;

import main.model.Video;

import java.util.List;

public interface VideoRepository {
    void save(Video video);

    List<Video> findAll();
}