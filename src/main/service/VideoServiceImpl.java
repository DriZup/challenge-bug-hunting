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
        validateVideo(video);
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }


    private void validateVideo(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("O Video não pode ser nulo!");
        }
        if (video.getTitulo() == null) {
            throw new IllegalArgumentException("O título do vídeo não pode ser vazio");
        }
        if (video.getDescricao() == null) {
            throw new IllegalArgumentException("A descrição do vídeo não pode ser vazia!");
        }
        if (video.getDuracao() <= 0) {
            throw new IllegalArgumentException("A duração do vídeo deve ser um número positivo!");
        }
        if (video.getCategoria() == null) {
            throw new IllegalArgumentException("A categoria não pode ser vazia!");
        }
        if (video.getDataPublicacao() == null) {
            throw new IllegalArgumentException("A data de publicação não pode ser vazia!");
        }
    }
}