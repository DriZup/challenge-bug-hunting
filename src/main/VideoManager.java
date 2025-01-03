package main;

import main.model.Video;
import main.service.VideoService;
import main.strategy.SearchStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VideoManager {
    private final VideoService videoService;
    private final SearchStrategy searchStrategy;
    private final Scanner scanner;

    public VideoManager(VideoService videoService, SearchStrategy searchStrategy) {
        this.videoService = videoService;
        this.searchStrategy = searchStrategy;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            displayMenu();
            int option = getUserInputAsInt("Escolha uma opção: ");

            switch (option) {
                case 1 -> addVideo();
                case 2 -> listVideos();
                case 3 -> searchVideos();
                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    closeScanner();
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Adicionar vídeo");
        System.out.println("2. Listar vídeos");
        System.out.println("3. Buscar vídeos");
        System.out.println("4. Sair");
    }

    private void addVideo() {
        String title = getUserInput("Digite o título do vídeo: ");
        String description = getUserInput("Digite a descrição do vídeo: ");
        int duration = getUserInputAsInt("Digite a duração do vídeo (em minutos): ");
        String category = getUserInput("Digite a categoria do vídeo: ");
        String publicationDateStr = getUserInput("Digite a data de publicação (dd/MM/yyyy): ");

        try {
            Date publicationDate = parseDate(publicationDateStr);
            Video video = new Video(title, description, duration, category, publicationDate);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        }catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo. Verifique os dados e tente novamente.");
        }
    }

    private void listVideos() {
        List<Video> videos = videoService.listVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            videos.forEach(System.out::println);
        }
    }

    private void searchVideos() {
        String query = getUserInput("Digite o título para busca: ");

        if(query.isEmpty()){
            System.out.println("A busca não pode ser vazia. Por favor, insira um título válido.");
            return;
        }
        List<Video> results = searchStrategy.search(videoService.listVideos(), query);
        if (results.isEmpty()) {
            System.out.printf("Nenhum vídeo encontrado para a busca: \"%s\".%n", query);
        } else {
            System.out.println("Resultados encontrados:");

            results.forEach(System.out::println);
        }
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getUserInputAsInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    private Date parseDate(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(dateStr);
    }

    private void closeScanner() {
        scanner.close();
    }
}
