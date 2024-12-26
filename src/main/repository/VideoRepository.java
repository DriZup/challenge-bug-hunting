package main.repository;

import main.model.Video;

import java.util.List;

public interface VideoRepository {


    void saveAll(List<Video> videos);

    List<Video> findAll();
}