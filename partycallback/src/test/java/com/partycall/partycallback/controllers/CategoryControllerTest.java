package com.partycall.partycallback.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.partycall.partycallback.models.Category;
import com.partycall.partycallback.services.CategoryService;

public class CategoryControllerTest {

     @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private Category category1;
    private Category category2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        category1 = new Category();
        category1.setCategoryId(1);
        category1.setName("Technology");

        category2 = new Category();
        category2.setCategoryId(2);
        category2.setName("Networking");
    }

    @Test
    public void testFindAllCategories() {
       
        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryService.findAllCategories()).thenReturn(categories);

       
        ResponseEntity<List<Category>> response = categoryController.findAllCategoris();

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
        verify(categoryService, times(1)).findAllCategories();
    }

    @Test
    public void testGetCategoryById() {
        
        int categoryId = 1;
        when(categoryService.findCategoryById(categoryId)).thenReturn(category1);

        
        ResponseEntity<Category> response = categoryController.getCategoryById(categoryId);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category1, response.getBody());
        verify(categoryService, times(1)).findCategoryById(categoryId);
    }

    @Test
    public void testCreateCategory() {
        
        when(categoryService.saveCategory(category1)).thenReturn(category1);

       
        ResponseEntity<Category> response = categoryController.createCategory(category1);

       
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category1, response.getBody());
        verify(categoryService, times(1)).saveCategory(category1);
    }

    @Test
    public void testEditCategory() {
        
        int categoryId = 1;
        when(categoryService.editCategory(categoryId, category1)).thenReturn(category1);

        
        ResponseEntity<Category> response = categoryController.editCategory(categoryId, category1);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(category1, response.getBody());
        verify(categoryService, times(1)).editCategory(categoryId, category1);
    }

    @Test
    public void testDeleteCategory() {
        
        int categoryId = 1;

       
        ResponseEntity<Category> response = categoryController.deleteCategory(categoryId);

        
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).deleteCategoryById(categoryId);
    }
    
}
