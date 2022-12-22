package org.organey.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.organey.data.dao.ProductDao;
import org.organey.data.driver.MySQLDriver;
import org.organey.data.model.Product;
import org.organey.data.model.User;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void insert(Product product) {
        // TODO Auto-generated method stub
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "INSERT INTO PRODUCTS(id, name, description, image, price, quantity, category_id) VALUES(NULL,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.name);
            stmt.setString(2, product.description);
            stmt.setString(3, product.image);
            stmt.setFloat(4, product.price);
            stmt.setInt(5, product.quantity);
            stmt.setInt(6, product.categoryId);
            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void update(Product product) {
        // TODO Auto-generated method stub
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "UPDATE PRODUCTS SET NAME=?, DESCRIPTION=?, IMAGE=?, PRICE=?, QUANTITY =?, CATEGORY_ID =? WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.name);
            stmt.setString(2, product.description);
            stmt.setString(3, product.image);
            stmt.setFloat(4, product.price);
            stmt.setInt(5, product.quantity);
            stmt.setInt(6, product.categoryId);
            stmt.setInt(7, product.id);

            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public void delete(int productId) {
        // TODO Auto-generated method stub
        try {
            Connection conn = MySQLDriver.getInstance().getConnection();
            String sql = "DELETE FROM PRODUCTS WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, productId);
            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public Product find(int productId) {
        // TODO Auto-generated method stub
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM PRODUCTS WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                return new Product(id, name, description, image, price, quantity, categoryId);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        List<Product> productList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM PRODUCTS";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                productList.add(new Product(id, name, description, image, price, quantity, categoryId));
            }
        } catch (SQLException ex) {
        }

        return productList;
    }

    @Override
    public List<Product> find_five() {
        // TODO Auto-generated method stub
        List<Product> productList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM PRODUCTS LIMIT 5";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                productList.add(new Product(id, name, description, image, price, quantity, categoryId));
            }
        } catch (SQLException ex) {
        }

        return productList;
    }
    @Override
    public List<Product> find_ten() {
        // TODO Auto-generated method stub
        List<Product> productList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM PRODUCTS LIMIT 10";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                productList.add(new Product(id, name, description, image, price, quantity, categoryId));
            }
        } catch (SQLException ex) {
        }

        return productList;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        // TODO Auto-generated method stub
        List<Product> productList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM PRODUCTS WHERE CATEGORY_ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoryId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                productList.add(new Product(id, name, description, image, price, quantity, categoryId));
            }
        } catch (SQLException ex) {
        }

        return productList;
    }

    @Override
    public List<Product> findByName(String key) {
        List<Product> productList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM PRODUCTS WHERE NAME LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + key + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                int categoryId = rs.getInt("category_id");
                productList.add(new Product(id, name, description, image, price, quantity, categoryId));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return productList;
    }
}
