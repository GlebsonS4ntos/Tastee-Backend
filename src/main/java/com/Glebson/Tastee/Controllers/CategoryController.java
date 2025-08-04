package com.Glebson.Tastee.Controllers;

import com.Glebson.Tastee.Data.Dto.CategoryDto;
import com.Glebson.Tastee.Services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(service.getAll());
    }

}
