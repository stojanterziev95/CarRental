package com.example.car_rental.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    @Test
    public void testCategoryGettersAndSetters() {
        Category category = new Category();
        category.setId(1L);
        category.setCategoryName(CarCategory.SUV);
        category.setDescription("Sport Utility Vehicle");

        assertEquals(1L, category.getId());
        assertEquals(CarCategory.SUV, category.getCategoryName());
        assertEquals("Sport Utility Vehicle", category.getDescription());
    }
}
