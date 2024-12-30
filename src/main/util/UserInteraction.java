package main.util;

import main.model.Video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
        System.out.println("1. Adicionar vídeo");
        System.out.println("2. Listar vídeos");
        System.out.println("3. Pesquisar vídeo por título");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public int getOption() {
        return scanner.nextInt();
    }

    public Video getVideoDetails() {
        try {
            scanner.nextLine(); // Consumir a quebra de linha
            System.out.print("Digite o título do vídeo: ");
            String titulo = scanner.nextLine();
            System.out.print("Digite a descrição do vídeo: ");
            String descricao = scanner.nextLine();

            int duracao;
            while (true) {
                System.out.print("Digite a duração do vídeo (em minutos): ");
                try {
                    duracao = Integer.parseInt(scanner.nextLine());
                    if (duracao > 0) {
                        break;
                    } else {
                        System.out.println("A duração deve ser um número positivo. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, insira um número válido para a duração.");
                }
            }

            System.out.print("Digite a categoria do vídeo: ");
            String categoria = scanner.nextLine();

            Date datapublicacao;
            while (true) {
                System.out.print("Digite a data de publicação (dd/MM/yyyy): ");

                String dataStr = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                try {
                    datapublicacao = sdf.parse(dataStr);
                    break; // Sai do loop se a data for válida
                } catch (ParseException e) {
                    System.out.println("Data inválida. Por favor, insira uma data no formato dd/MM/yyyy.");
                }
            }
            return new Video(titulo, descricao, duracao, categoria, datapublicacao);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar vídeo. Verifique os dados informados.");
            return null;
        }
    }

    public void showVideos(List<Video> videos) {
        if (videos.isEmpty()) {
            System.out.println("Nenhum vídeo encontrado.");
        } else {
            for (Video video : videos) {
                System.out.println(video);
            }
        }
    }

    public String getSearchQuery() {
        scanner.nextLine();
        System.out.print("Digite o título para busca: ");
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
