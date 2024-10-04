package com.example.car_rental.service;

import com.example.car_rental.enums.CarCategory;
import com.example.car_rental.models.Category;
import com.example.car_rental.repository.CategoryRepository;
import com.example.car_rental.serviceImplementation.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        category.setId(1L);
        category.setCategoryName(CarCategory.SUV);
    }

    @Test
    public void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        assertEquals(1, categoryService.getAllCategories().size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testGetCategoryById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        assertEquals(category, categoryService.getCategoryById(1L));
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCategory() {
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category, categoryService.createCategory(category));
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testUpdateCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);

        Category updatedCategory = new Category();
        updatedCategory.setCategoryName(CarCategory.SEDAN);
        categoryService.updateCategory(1L, updatedCategory);

        assertEquals("Sedan", category.getCategoryName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testDeleteCategory() {
        categoryService.deleteCategory(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
