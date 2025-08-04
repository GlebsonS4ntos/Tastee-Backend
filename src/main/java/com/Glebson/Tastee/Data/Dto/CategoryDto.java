package com.Glebson.Tastee.Data.Dto;

import com.Glebson.Tastee.Domain.Category;
import jakarta.persistence.Column;

import java.util.Objects;

public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Category c){
        this.id = c.getId();
        this.name = c.getName();
    }

    public Category convertToEntity(){
        Category c = new Category();
        c.setId(this.id);
        c.setName(this.name);
        return c;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategoryDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
