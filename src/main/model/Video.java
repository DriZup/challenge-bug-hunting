package main.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private static ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd/MM/yyyy"));
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private Date dataPublicacao;



    public Video(String titulo, String descricao, int duracao, String categoria, Date dataPublicacao) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio");
        }
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia");
        }
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero");
        }
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("A categoria não pode ser vazia");
        }

        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    // Métodos getters
    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    // Método para converter o objeto Video em uma String
    @Override
    public String toString() {
        SimpleDateFormat sdf = DATE_FORMATTER.get(); // Usando o SimpleDateFormat do ThreadLocal
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + sdf.format(dataPublicacao);
    }

    // Método estático para converter uma linha de texto em um objeto Video
    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            if (partes.length != 5) {
                throw new IllegalArgumentException("Linha mal formatada: " + linha);
            }

            String titulo = partes[0];
            String descricao = partes[1].trim();
            int duracao = Integer.parseInt(partes[2]);
            String categoria = partes[3];
            SimpleDateFormat sdf = DATE_FORMATTER.get();
            Date dataPublicacao = sdf.parse(partes[4]);

            if (descricao.isEmpty()) {
                descricao = "Sem descrição";
            }

            return new Video(titulo, descricao, duracao, categoria, dataPublicacao);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Erro ao converter data na linha: " + linha, e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Erro ao converter número na linha: " + linha, e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao converter a linha: " + linha, e);
        }
    }
}