package main.service;

import main.model.Video;
import main.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class VideoService {

    private static final String FILE_PATH = "videos.txt";



    public void addVideo(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("Video cannot be null");
        }
        FileUtils.writeToFile(FILE_PATH, video.toString());
    }

    public List<Video> listVideos() {
        List<Video> list = new ArrayList<>();
        for (String s : FileUtils.readFromFile(FILE_PATH)) {
            try {
                Video video = Video.fromString(s);
                list.add(video);
            } catch (Exception e) {
                throw new RuntimeException("Error parsing video data: " + s, e);
            }
        }
        return list;
    }
}