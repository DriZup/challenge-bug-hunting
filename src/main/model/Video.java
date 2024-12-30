package main.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Video {
    public static final List<Object> CATEGORIAS_VALIDAS = Arrays.asList("Filme", "Série", "Documentário");
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private Date dataPublicacao;
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public Video(String titulo, String descricao, int duracao, String categoria, Date dataPublicacao) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou vazio.");
        }
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser nula ou vazia.");
        }
        if (duracao <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }
        if (categoria == null || categoria.isEmpty() || !CATEGORIAS_VALIDAS.contains(categoria)) {
            throw new IllegalArgumentException("A categoria deve ser válida. Categorias permitidas: " + CATEGORIAS_VALIDAS);
        }
        if (dataPublicacao == null) {
            throw new IllegalArgumentException("A data de publicação não pode ser nula.");
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
        return String.format("%s;%s;%d;%s;%s", titulo, descricao, duracao, categoria, sdf.format(dataPublicacao));
    }

    public static Video fromString(String line) {
        try {
            String[] partes = line.split(";");
            if (partes.length != 5) {
                throw new IllegalArgumentException("Formato inválido para deserialização.");
            }

            String titulo = partes[0];
            String descricao = partes[1];
            int duracao = Integer.parseInt(partes[2]);
            String categoria = partes[3];
            Date dataPublicacao = parseData(partes[4]);

            return new Video(titulo, descricao, duracao, categoria, dataPublicacao);
        } catch (Exception e) {
            System.out.println("Erro ao deserializar vídeo: " + e.getMessage());
            return null;
        }
    }

    private static Date parseData(String data) throws ParseException {
        String[] formatos = {
                "dd/MM/yyyy",
                "EEE MMM dd HH:mm:ss zzz yyyy"
        };

        for (String formato : formatos) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.ENGLISH); // Define o Locale como ENGLISH
                sdf.setLenient(true);
                return sdf.parse(data);
            } catch (ParseException ignored) {

            }
        }

        throw new ParseException("Formato de data inválido: " + data, 0);
    }
}