package co.jmurillo.java.swing.jdbc.repositories;

import co.jmurillo.java.swing.jdbc.models.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product delete(Long id);
}
