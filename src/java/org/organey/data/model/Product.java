package org.organey.data.model;

import org.organey.data.dao.DatabaseDao;

public class Product {

    public int id;
    public String name;
    public String description;
    public String image;
    public float price;
    public int quantity;
    public int categoryId;

    public Product(String name, String description, String image, float price, int quantity, int categoryId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public Product(int id, String name, String description, String image, float price, int quantity, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public Category getCategory() {
        return DatabaseDao.getInstance().getCategoryDao().find(categoryId);
    }
}
