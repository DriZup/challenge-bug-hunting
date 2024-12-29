package main.repository;

import main.FileHandler.FileHandler;
import main.model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileVideoRepository implements VideoRepository {
    private final FileHandler fileHandler;

    public FileVideoRepository(String filePath) {
        this.fileHandler = new FileHandler(filePath);
    }

    @Override
    public void save(Video video) {
        fileHandler.writeToFile(video.toString());
    }

    @Override
    public List<Video> findAll() {
        return fileHandler.readFromFile().stream()
                .map(Video::fromString)
                .filter(video -> video != null)
                .collect(Collectors.toList());
    }
}


