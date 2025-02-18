package co.jmurillo.java.swing.jdbc.models;

/**
 * La clase Product representa un producto con atributos como id, nombre, precio y cantidad.
 * Incluye constructores, getters y setters para estos atributos.
 */
public class Product {

    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    /**
     * Constructor por defecto para la clase Product.
     */
    public Product() {
    }

    /**
     * Constructor parametrizado para la clase Product.
     *
     * @param id       el identificador único del producto
     * @param name     el nombre del producto
     * @param price    el precio del producto
     * @param quantity la cantidad del producto
     */
    public Product(Long id, String name, Integer price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Obtiene el identificador único del producto.
     *
     * @return el id del producto
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     *
     * @param id el id a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre del producto
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param name el nombre a establecer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return el precio del producto
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price el precio a establecer
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Obtiene la cantidad del producto.
     *
     * @return la cantidad del producto
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param quantity la cantidad a establecer
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}