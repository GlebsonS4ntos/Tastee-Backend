package com.Glebson.Tastee.Data.Dto;

import com.Glebson.Tastee.Domain.Category;
import com.Glebson.Tastee.Domain.Post;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class PostDto {
    private Long id;
    private String title;
    private String content;
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
