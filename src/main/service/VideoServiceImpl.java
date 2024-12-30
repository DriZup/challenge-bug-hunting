package main.service;

import main.model.Video;
import main.repository.VideoRepository;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoRepository repository;

    public VideoServiceImpl(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addVideo(Video video) {
        validarVideo(video);
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }

    private static void validarVideo(Video video){
        if(video == null){
            throw new IllegalArgumentException("O vídeo não pode ser nulo!");
        }
        if(video.getTitulo() == null){
            throw new IllegalArgumentException("O título do vídeo não pode ser nulo!");
        }
        if(video.getDescricao() == null){
            throw new IllegalArgumentException("A descrição do vídeo não pode ser nula!");
        }
        if(video.getDuracao() <= 0 ){
            throw new IllegalArgumentException("A duração do vídeo deve ser um número positivo!");
        }
        if(video.getCategoria() == null){
            throw new IllegalArgumentException("A categoria do vídeo não pode ser nula!");
        }
        if(video.getDataPublicacao() == null){
            throw new IllegalArgumentException("A Data do vídeo não pode ser nula!");
        }
    }
}