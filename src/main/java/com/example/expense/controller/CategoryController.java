package com.example.expense.controller;

import com.example.expense.model.Category;
import com.example.expense.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    Collection<Category> categories(){
        //return is same as SELECT * from category (JPA handles this under the hood)
        return categoryRepository.findAll();
    }

    //get category by id
    @GetMapping("/category/{id}")
    ResponseEntity<?> getCategory(@PathVariable Long id){
        //returns the category, "optional" for returning nothing (when the id is not valid)
        Optional<Category> category = categoryRepository.findById(id);
        //if above does return something, map it to response, and if ok put response into body and send it back to browser.
        return category.map(response -> ResponseEntity.ok().body(response))
                //else create a responseEntity with not found status and send back to browser.
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //to add a completely new object
    @PostMapping("/category")
    ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        Category result = categoryRepository.save(category);
        return ResponseEntity.created(new URI("/api/category" + result.getId())).body(result);
    }

    //updating an existing object with id
    @PutMapping("/category/{id}")
    ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category){
        Category result = categoryRepository.save(category);
        return ResponseEntity.ok().body(result);
    }

    //deleting an object with id
    @DeleteMapping("/category/{id}")
    ResponseEntity deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }
}
