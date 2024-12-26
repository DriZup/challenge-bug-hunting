package main.service;

import main.model.Video;
import main.repository.VideoRepository;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoRepository repository;


    public VideoServiceImpl(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addVideo(Video video) {
        video.validarVideo();
        repository.saveAll(List.of(video));
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }
}