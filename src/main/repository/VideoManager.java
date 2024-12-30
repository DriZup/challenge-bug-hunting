package main.repository;

import main.model.Video;
import main.util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class VideoManager implements VideoRepository {
    private final FileHandler fileHandler;

    public VideoManager(String file) {
      this.fileHandler = new FileHandler(file);
    }


    @Override
    public void save(Video video) {
        fileHandler.writeLine(video.toString());
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        List<String> lines = fileHandler.readAllLines();
        for (String line : lines) {
            Video video = Video.fromString(line);
            if (video != null) {
                videos.add(video);
            }
        }
        return videos;
    }
}