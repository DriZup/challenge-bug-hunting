package main.repository;

import main.model.Video;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileVideoRepository implements VideoRepository {
    private final static String FILENAME = "videos.txt";


    @Override
    public void saveAll(List<Video> videos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, false))) {
            for (Video video : videos) {
                writer.write(video.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo " + FILENAME, e);
        }
    }

    @Override
    public List<Video> findAll() {
        List<Video> videos = new ArrayList<>();
        File file = new File(FILENAME);

        if (!file.exists()) {
            return videos;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Video video = Video.fromString(line);
                    if (video != null) {
                        videos.add(video);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Linha ignorada devido a erro de formatação: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo " + FILENAME, e);
        }
        return videos;
    }
}