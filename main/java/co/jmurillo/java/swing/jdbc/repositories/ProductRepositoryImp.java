package co.jmurillo.java.swing.jdbc.repositories;

import co.jmurillo.java.swing.jdbc.db.ConnectionJdbc;
import co.jmurillo.java.swing.jdbc.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ProductRepository que proporciona métodos para interactuar con la base de datos de productos.
 */
public class ProductRepositoryImp implements ProductRepository {

    /**
     * Recupera una lista de todos los productos disponibles en la base de datos.
     *
     * @return una lista de productos
     */
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = ConnectionJdbc.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")){
                while (rs.next()) {
                    Product product = new Product(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("quantity"));
                    products.add(product);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Recupera un producto específico basado en su identificador único.
     *
     * @param id el identificador único del producto
     * @return el producto correspondiente al identificador, o null si no se encuentra
     */
    @Override
    public Product findById(Long id) {
        Product product = null;
        try (Connection conn = ConnectionJdbc.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE id=?");){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    product = new Product(rs.getLong("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param product el producto a guardar
     * @return el producto guardado, o null si la operación falla
     */
    @Override
    public Product save(Product product) {
        String sql = "";
        if (product.getId() != null && product.getId() > 0) {
            sql = "update products set name=?, price=?, quantity=? where id = ?";
        } else {
            sql = "insert into products(name, price, quantity) values(?,?,?)";
        }

        try (Connection conn = ConnectionJdbc.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            if (product.getId() != null && product.getId() > 0) {
                stmt.setLong(4, product.getId());
            }
            int affectedRow = stmt.executeUpdate();
            if (affectedRow > 0 && (product.getId() == null || product.getId() == 0)) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        product.setId(rs.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * Elimina un producto de la base de datos basado en su identificador único.
     *
     * @param id el identificador único del producto a eliminar
     * @return el producto eliminado, o null si no se encuentra o si la operación falla
     */
    @Override
    public Product delete(Long id) {
        try (Connection conn = ConnectionJdbc.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE id=?");){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}