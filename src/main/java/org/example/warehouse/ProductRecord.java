package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {
    private final UUID UUID_value;
    private final String UUID_name;
    private final Category categoryName;
    private final BigDecimal bigDecimal;
    private BigDecimal price;

    public ProductRecord(UUID uuidValue, String uuidName, Category categoryName, BigDecimal bigDecimal) {
        UUID_value = uuidValue;
        UUID_name = uuidName;
        this.categoryName = categoryName;
        this.bigDecimal = bigDecimal;
    }


    public UUID uuid() {
        if (UUID_value == null) {
            return UUID.randomUUID();
        }
        return UUID_value;
    }

    public BigDecimal price() {
        if (bigDecimal == null) {
            return new BigDecimal(0);
        }
        return bigDecimal;
    }

    public String getUUID_name() {
        return UUID_name;
    }

    public Category getCategoryName() {
        return categoryName;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category category() {
        return categoryName;
    }
}
