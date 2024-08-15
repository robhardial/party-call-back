package com.partycall.partycallback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partycall.partycallback.models.Category;
import com.partycall.partycallback.repositiories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Finds a Category by their ID.
     *
     * @param id the ID of the Category to find
     * @return the Category with the specified ID, or null if no Category is found
     */
    public Category findCategoryById(int id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {

            return category.get();
        }

        return null;
    }

    /**
     * Saves the given category into the repository.
     *
     * @param category The category to be saved.
     * @return The saved category.
     */
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Edits an existing category in the system with the specified ID.
     *
     * @param id       The ID of the category to edit.
     * @param category The updated category object with the new email and password.
     * @return The edited category object.
     */
    public Category editCategory(int id, Category category) {
        Optional<Category> existingCategoryOptional = categoryRepository.findById(id);

        if (existingCategoryOptional.isPresent()) {

            Category existingCategory = existingCategoryOptional.get();

            if (category.getName() != null) {
                existingCategory.setName(category.getName());
            }

            if (category.getEvents() != null) {
                existingCategory.setEvents(category.getEvents());
            }

            return categoryRepository.save(existingCategory);
        } else {
            return categoryRepository.save(category);
        }
    }

    /**
     * Deletes a category by their ID.
     *
     * @param id the ID of the category to be deleted
     */
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

}
