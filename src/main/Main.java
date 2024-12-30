package main;

import main.model.Video;
import main.repository.FileVideoRepository;
import main.service.VideoService;
import main.service.VideoServiceImpl;
import main.strategy.SearchStrategy;
import main.strategy.TitleSearchStrategy;
import main.util.UserInteraction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInteraction userInteraction = new UserInteraction();
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        while (true) {
          userInteraction.showMenu();
          int option = userInteraction.getOption();

          switch(option){
              case 1 -> {
                  Video video = userInteraction.getVideoDetails();
                  if (video != null) {
                      videoService.addVideo(video);
                      userInteraction.showMessage("Vídeo adicionado com sucesso!");
                  }
              }
              case 2 -> {
                  List<Video> videos = videoService.listVideos();
                  userInteraction.showVideos(videos);
              }
              case 3 -> {
                  String query = userInteraction.getSearchQuery();
                  List<Video> results = searchStrategy.search(videoService.listVideos(), query);
                  userInteraction.showVideos(results);
              }
              case 4 -> {
                  userInteraction.showMessage("Saindo do sistema...");
                  return;
              }
              default -> userInteraction.showMessage("Opção inválida.");
          }
        }
    }
}