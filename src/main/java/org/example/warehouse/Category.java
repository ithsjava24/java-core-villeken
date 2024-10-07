package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {

    private static final Map<String, Category> instances = new HashMap<>();

    private final String categoryName;

    private Category(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        this.categoryName = categoryName.substring(0, 1).toUpperCase().concat(categoryName.substring(1));
    }

    public static Category of(String name) {
        return instances.computeIfAbsent(name, Category::new);
    }


    public String getName() {
        return categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;

        return Objects.equals(categoryName,
                category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(categoryName);
    }

    @Override
    public String toString() {
        return categoryName;
    }
}

