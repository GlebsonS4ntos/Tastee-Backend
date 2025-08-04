package com.Glebson.Tastee.Data.Dto;

import com.Glebson.Tastee.Domain.Post;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PostDto {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50, message = "O Titulo deve ter entre 3 e 50 caracteres.")
    private String title;
    @NotBlank
    @Size(min = 10, max = 2000, message = "O Conteudo deve ter entre 10 e 2000 caracteres.")
    private String content;
    @Size(min = 1, message = "Deve ter ao menos 1 Categoria.")
    private Set<CategoryDto> categories;

    public PostDto() {
    }

    public PostDto(Post p){
        this.id = p.getId();
        this.title = p.getTitle();
        this.content = p.getContent();
        this.categories = p.getCategories().stream().map(c -> new CategoryDto(c)).collect(Collectors.toSet());
    }

    public Post convertToEntity(){
        Post p = new Post();
        p.setTitle(this.title);
        p.setContent(this.content);
        p.setCategories(this.categories.stream().map(c -> c.convertToEntity()).collect(Collectors.toSet()));

        return p;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PostDto postDto)) return false;
        return Objects.equals(getId(), postDto.getId()) && Objects.equals(getTitle(), postDto.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}
