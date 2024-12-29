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
        setTitulo(titulo);
        setDescricao(descricao);
        setDuracao(duracao);
        setCategoria(categoria);
        setDataPublicacao(dataPublicacao);
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    private void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    private void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser nula!");
        }
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    private void setDuracao(int duracao) {
        if (duracao <= 0) {
            throw new IllegalArgumentException("Duração deve ser um número positivo!");
        }
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }

    private void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia!");
        }
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    private void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio!");
        }
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + sdf.format(dataPublicacao);
    }

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], sdf.parse(partes[4]));
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }
}