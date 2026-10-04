package com.partycall.partycallback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partycall.partycallback.models.Category;
import com.partycall.partycallback.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * Retrieves all categorys from the database.
     *
     * @return A ResponseEntity containing a list of Category objects representing
     *         all
     *         categorys in the database.
     */
    @GetMapping
    public ResponseEntity<List<Category>> findAllCategoris() {
        List<Category> categorys = categoryService.findAllCategories();
        return new ResponseEntity<List<Category>>(categorys, HttpStatus.OK);
    }

    /**
     * Retrieves a Category from the database based on the provided ID.
     *
     * @param id the ID of the Category to retrieve
     * @return a ResponseEntity containing the requested Category if found, or
     *         HttpStatus.OK if successful
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    /**
     * Creates a new category with the provided category information.
     *
     * @param category The category object containing the category information.
     * @return A ResponseEntity object with the created category and HTTP status
     *         code
     *         201 (Created).
     */
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category newCategory = categoryService.saveCategory(category);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    /**
     * Edits an existing category in the system with the specified ID.
     *
     * @param id       The ID of the category to edit.
     * @param category The updated category object with the new email and password.
     * @return The edited category object.
     */
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable int id, @RequestBody Category category) {
        Category updatedCategory = categoryService.editCategory(id, category);
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
    }

    /**
     * Deletes a category by their ID.
     *
     * @param id the ID of the category to be deleted
     * @return a response entity indicating success with no content
     */
    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

}
