package org.organey.data.dao;

import java.util.List;

import org.organey.data.model.Product;

public interface ProductDao {

    public void insert(Product product);

    public void update(Product product);

    public void delete(int productId);

    public Product find(int productId);

    public List<Product> findAll();
    
    public List<Product> find_five();
    
    public List<Product> find_ten();

    public List<Product> findByName(String name);

    public List<Product> findByCategoryId(int categoryId);
}
