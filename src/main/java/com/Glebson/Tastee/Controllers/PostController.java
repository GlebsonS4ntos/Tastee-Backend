package com.Glebson.Tastee.Controllers;

import com.Glebson.Tastee.Data.Dto.PostDto;
import com.Glebson.Tastee.Exceptions.NotFoundException;
import com.Glebson.Tastee.Services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAll(){
        return ResponseEntity.ok(service.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPost(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable(name = "id") Long id){
        service.removePostById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePost(@Valid @RequestBody PostDto dto, @PathVariable Long id){
        service.updatePost(dto, id);
        return ResponseEntity.noContent().build();
    }
}
