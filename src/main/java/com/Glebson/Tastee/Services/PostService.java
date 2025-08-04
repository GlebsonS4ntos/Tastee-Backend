package com.Glebson.Tastee.Services;

import com.Glebson.Tastee.Data.Dto.PostDto;
import com.Glebson.Tastee.Domain.Post;
import com.Glebson.Tastee.Exceptions.BadRequestException;
import com.Glebson.Tastee.Exceptions.NotFoundException;
import com.Glebson.Tastee.Repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto> getAllPosts(){
        return repository.findAll().stream().map(p -> new PostDto(p)).collect(Collectors.toList());
    }

    public PostDto getPostById(Long id){
        Post c = repository.findById(id).orElseThrow(() -> new NotFoundException("Receita não encontrada."));
        return new PostDto(c);
    }

    public void removePostById(Long id){
        Post p = repository.findById(id).orElseThrow(() -> new NotFoundException("Receita não encontrada."));

        p.clearCategories();
        repository.save(p);

        repository.delete(p);
    }

    public PostDto createPost(PostDto dto){
        Post p = repository.save(dto.convertToEntity());
        return new PostDto(p);
    }

    public void updatePost(PostDto dto, Long id){
        if (!dto.getId().equals(id)) throw new BadRequestException("Receita não é a mesma da url.");

        repository.findById(id).orElseThrow(() -> new NotFoundException("Receita não encontrada."));
        Post p = dto.convertToEntity();
        p.setId(id);
        repository.save(p);
    }
}
