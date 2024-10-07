package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private static final HashMap<String, Warehouse> instances = new HashMap<>();
    private final String name;
    private final List<ProductRecord> productRecords = new ArrayList<>();
    private final List<ProductRecord> changedProductRecords = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance() {
        return new Warehouse("Default");
    }


    public static Warehouse getInstance(String name) {
        if (instances.containsKey(name)) {
            return (Warehouse) instances.get(name);
        } else {
            Warehouse warehouse = new Warehouse(name);
            instances.put(name, warehouse);
            return warehouse;
        }
    }

    public boolean isEmpty() {
        return productRecords.isEmpty();
    }

    public List<ProductRecord> getProducts() {
        return productRecords;
    }

    public List<ProductRecord> getChangedProducts() {
        return List.copyOf(changedProductRecords);
    }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        List<ProductRecord> filteredProducts = productRecords.stream().filter(product -> product.uuid().equals(uuid)).toList();
        if (filteredProducts.isEmpty()) return Optional.empty();
        return Optional.of(filteredProducts.getFirst());
    }


    public ProductRecord addProduct(UUID UUID_value, String name, Category categoryName, BigDecimal bigDecimal) {
        for (ProductRecord productRecord : productRecords) {
            if (productRecord.uuid().equals(UUID_value)) {
                throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
            }
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Product name can't be null or empty.");
            }
            if (categoryName == null) {
                throw new IllegalArgumentException("Category can't be null.");
            }
        }
        var product = new ProductRecord(UUID_value, name, categoryName, bigDecimal);
        productRecords.add(product);
        return product;
    }

    public void updateProductPrice(UUID uuid, BigDecimal bigDecimal) {
        for (ProductRecord productRecord : productRecords) {
            if (productRecord.uuid().equals(uuid)) {

            }
        }
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        Map<Category, List<ProductRecord>> productsByCategory = new HashMap<>();
        for (ProductRecord product : productRecords) {
            Category category = product.category();
            List<ProductRecord> productsInCategory = productsByCategory.getOrDefault(category, new ArrayList<>());
            productsInCategory.add(product);
            productsByCategory.put(category, productsInCategory);
        }
        return Collections.unmodifiableMap(productsByCategory);
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return productRecords.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }

}
