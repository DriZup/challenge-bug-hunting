package main;

import main.model.Video;
import main.repository.FileVideoRepository;
import main.service.VideoService;
import main.service.VideoServiceImpl;
import main.strategy.SearchStrategy;
import main.strategy.TitleSearchStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        TitleSearchStrategy searchStrategy = new TitleSearchStrategy();

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    addVideo(scanner, videoService);
                    break;
                case 2:
                    listVideos(videoService);
                    break;
                case 3:
                    searchVideo(scanner, videoService, searchStrategy);
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }


    private static void addVideo(Scanner scanner, VideoService videoService) {
        try {
            System.out.print("Digite o título do vídeo: ");
            String titulo = scanner.nextLine();
            System.out.print("Digite a descrição do vídeo: ");
            String descricao = scanner.nextLine();
            System.out.print("Digite a duração do vídeo (em minutos): ");
            int duracao = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite a categoria do vídeo: ");
            String categoria = scanner.nextLine();
            System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataStr);
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");

        } catch (ParseException ex) {
            System.out.println("Erro ao formatar a data. Verifique o formato da data.");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo. Verifique os dados e tente novamente.");
            e.printStackTrace();
        }
    }

    private static void listVideos(VideoService videoService) {
        List<Video> videos = videoService.listVideos();
        if (videos.isEmpty()) {
            System.out.println("Nenhum video encontrado!");
        } else {
            videos.forEach(System.out::println);
        }
    }

    private static void searchVideo(Scanner scanner, VideoService videoService, TitleSearchStrategy searchStrategy) {
        System.out.println("Digite o título pra busca: ");
        String query = scanner.nextLine();
        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        if (resultados.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado com esse título: ");
        } else {
            resultados.forEach(System.out::println);
        }

        scanner.close();
    }
}