package main.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private Date dataPublicacao;

    public Video(String titulo, String descricao, int duracao, String categoria, Date dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%d;%s;%s", titulo, descricao, duracao, categoria, dataPublicacao);
    }

    public static Video fromString(String line) {
        try {
            String[] parts = line.split(";");
            return new Video(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    parts[3],
                    new Date(parts[4]));
        } catch (Exception e) {
            System.out.println("Erro ao deserializar vídeo: " + e.getMessage());
            return null; // Ignora erros de parsing
        }
    }
}