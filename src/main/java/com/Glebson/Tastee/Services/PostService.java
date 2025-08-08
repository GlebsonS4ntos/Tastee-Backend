package com.Glebson.Tastee.Services;

import com.Glebson.Tastee.Data.Dto.PostDto;
import com.Glebson.Tastee.Domain.Post;
import com.Glebson.Tastee.Exceptions.BadRequestException;
import com.Glebson.Tastee.Exceptions.NotFoundException;
import com.Glebson.Tastee.Repositories.CategoryRepository;
import com.Glebson.Tastee.Repositories.PostRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository repository;
    private final CloudinaryService imgService;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository repository, CloudinaryService imgService, CategoryRepository c) {
        this.imgService = imgService;
        this.repository = repository;
        this.categoryRepository = c;
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

        if (p.getImage() != null) imgService.deleteImage(p.getImage());

        p.clearCategories();
        repository.save(p);

        repository.delete(p);
    }

    public PostDto createPost(PostDto dto){
        dto.getCategories().forEach(c -> categoryRepository.findById(c.getId()).orElseThrow(() -> new NotFoundException("Categoria inválida.")));
        Post p = repository.save(dto.convertToEntity());
        return new PostDto(p);
    }

    public void updatePost(PostDto dto, Long id){
        if (!dto.getId().equals(id)) throw new BadRequestException("Receita não é a mesma da url.");

        dto.getCategories().forEach(c -> categoryRepository.findById(c.getId()).orElseThrow(() -> new NotFoundException("Categoria inválida.")));
        repository.findById(id).orElseThrow(() -> new NotFoundException("Receita não encontrada."));
        Post p = dto.convertToEntity();
        p.setId(id);
        repository.save(p);
    }
}
