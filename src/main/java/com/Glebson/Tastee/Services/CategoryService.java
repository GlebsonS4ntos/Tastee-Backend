package com.Glebson.Tastee.Services;

import com.Glebson.Tastee.Data.Dto.CategoryDto;
import com.Glebson.Tastee.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDto> getAll(){
        return repository.findAll().stream().map((c) -> new CategoryDto(c)).collect(Collectors.toList());
    }
}
