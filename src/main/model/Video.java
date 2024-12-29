package main.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Video {
private static ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd/MM/yyyy"));   private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private Date dataPublicacao;

    public static final String DATE_FORMAT = "dd/MM/yyyy";


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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return titulo + ";" + descricao + ";" + duracao + ";" + categoria + ";" + sdf.format(dataPublicacao);
    }


        public static Video fromString(String linha) {
            try {
                String[] partes = linha.split(";");
                if (partes.length != 5) {
                    throw new IllegalArgumentException("Linha mal formatada: " + linha);
                }
                SimpleDateFormat sdf = DATE_FORMATTER.get();
                return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], sdf.parse(partes[4]));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Erro ao converter data na linha: " + linha, e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Erro ao converter número na linha: " + linha, e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao converter a linha: " + linha, e);
        }
    }
}