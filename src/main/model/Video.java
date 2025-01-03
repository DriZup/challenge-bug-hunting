package main.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private Date dataPublicacao;


    public Video(String titulo, String descricao, int duracao, String categoria, Date dataPublicacao) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou vazio.");
        }
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser nula ou vazia.");
        }
        if (duracao < 0) {
            throw new IllegalArgumentException("A duração não pode ser negativa.");
        }
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("A categoria não pode ser nula ou vazia.");
        }
        if (dataPublicacao == null) {
            throw new IllegalArgumentException("A dataPublicacao não pode ser nula.");
        }

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + sdf.format(dataPublicacao);
    }

    public static Video fromString(String linha) throws ParseException {
        String[] partes = linha.split(";");
        if (partes.length != 5) {
            throw new IllegalArgumentException("A linha deve conter exatamente 5 partes separadas por ';'.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], sdf.parse(partes[4]));
    }

}