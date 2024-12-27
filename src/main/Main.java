package main;

import main.model.Video;
import main.repository.FileVideoRepository;
import main.service.VideoService;
import main.service.VideoServiceImpl;
import main.strategy.SearchStrategy;
import main.strategy.TitleSearchStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository());
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (opcao == 1) {
                System.out.print("Digite o título do vídeo: ");
                String titulo = scanner.nextLine();
                System.out.print("Digite a descrição do vídeo: ");
                String descricao = scanner.nextLine();
                int duracao = -1;
                while (duracao <= 0) {
                    System.out.print("Digite a duração em minutos: ");
                    try {
                        duracao = scanner.nextInt();
                        if (duracao <= 0) {
                            System.out.println("A duração deve ser um número positivo.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro de entrada. A duração deve ser um número.");
                        scanner.nextLine(); // Consumir a quebra de linha do erro
                    }
                }
                scanner.nextLine(); // Consumir a quebra de linha
                System.out.print("Digite a categoria do vídeo: ");
                String categoria = scanner.nextLine();

                Date dataPublicacao = null;
                while (dataPublicacao == null) {
                    System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
                    String dataStr = scanner.nextLine();
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        dataPublicacao = sdf.parse(dataStr);
                    } catch (Exception e) {
                        System.out.println("Data inválida. Tente novamente.");
                    }
                }

                // Adicionar o vídeo
                Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
                videoService.addVideo(video);
                System.out.println("Vídeo adicionado com sucesso!");

            } else if (opcao == 2) {
                List<Video> videos = videoService.listVideos();
                for (Video video : videos) {
                    System.out.println(video);
                }
            } else if (opcao == 3) {
                System.out.print("Digite o título para busca: ");
                String query = scanner.nextLine();
                List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
                for (Video video : resultados) {
                    System.out.println(video);
                }
            } else if (opcao == 4) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}