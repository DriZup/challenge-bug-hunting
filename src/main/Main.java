package main;

import main.service.VideoService;
import main.strategy.SearchStrategy;
import main.strategy.TitleSearchStrategy;

public class Main {
    public static void main(String[] args) {
        VideoService videoService = new VideoService();
        SearchStrategy searchStrategy = new TitleSearchStrategy();
        VideoManager videoManager = new VideoManager(videoService, searchStrategy);
        videoManager.run();
    }
}
