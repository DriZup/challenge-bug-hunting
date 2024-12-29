package main.service;

import main.model.Video;

import java.util.List;

public interface VideoService {
    void addVideo(Video video);
    List<Video> listVideos();
}