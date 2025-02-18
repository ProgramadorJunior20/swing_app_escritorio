package co.jmurillo.java.swing.jdbc;

import co.jmurillo.java.swing.jdbc.models.Product;
import co.jmurillo.java.swing.jdbc.repositories.ProductRepository;
import co.jmurillo.java.swing.jdbc.repositories.ProductRepositoryImp;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class JdbcSwingCrudProyect extends JFrame {
    private Container p;
    private JTextField nameField = new JTextField();
    private JTextField priceField = new JTextField();
    private JTextField quantifyField = new JTextField();
    private ProductTableModel tableModel = new ProductTableModel();

    private ProductRepository productRepository;

    private long id;
    private int row;

    // Constructor de la clase
    public JdbcSwingCrudProyect() {
        super("Swing: GUI con Bases De Datos MSQL");
        p = getContentPane();
        p.setLayout(new BorderLayout(20, 10));

        productRepository = new ProductRepositoryImp();

        // Panel del formulario
        JPanel formPanel = new JPanel(new GridLayout(4,2, 20, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JButton buttonSave = new JButton("Guardar");

        formPanel.add(new JLabel("Nombre: "));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Precio: "));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Cantidad: "));
        formPanel.add(quantifyField);

        formPanel.add(new JLabel(""));
        formPanel.add(buttonSave);

        // Evento de guardar
        buttonSave.addActionListener(e -> {
            String name = nameField.getText();
            int price = 0;
            int quantity = 0;
            try {
                price = Integer.parseInt(priceField.getText());
            } catch (NumberFormatException ex) {}

            try {
                quantity = Integer.parseInt(quantifyField.getText());
            } catch (NumberFormatException ex) {}

            List<String> errors = new ArrayList<>();

            // Validaciones
            if (name.isBlank()) {
                errors.add("Debe ingresar el nombre!");
            }

            if (price == 0) {
                errors.add("El precio es requerido, debe ser un valor numeric!");
            }

            if (quantity == 0) {
                errors.add("La cantidad es requerida, No debe ser cero!");
            }

            if (errors.size() > 0) {
                JOptionPane.showMessageDialog(null, errors.toArray(),
                        "Error en la validacion!", JOptionPane.ERROR_MESSAGE);
            } else {
                // Guardar el producto
                Product productDB = productRepository.save(new Product(id == 0?null: id, name, price, quantity));
                if (id == 0) {
                    Object[] product = new Object[]{productDB.getId(), name, price, quantity, "Eliminar"};
                    tableModel.getRows().add(product);
                    tableModel.fireTableDataChanged();

                    System.out.println(product[0]);
                    System.out.println(product[1]);
                    System.out.println(product[2]);
                } else if (id > 0) {
                    tableModel.setValueAt(id, row, 0);
                    tableModel.setValueAt(name, row, 1);
                    tableModel.setValueAt(price, row, 2);
                    tableModel.setValueAt(quantity, row, 3);
                }
                reset();
            }
        });

        // Panel de la tabla
        JPanel tablePanel = new JPanel(new FlowLayout());

        JTable jTable = new JTable();
        jTable.setModel(this.tableModel);

        // Evento de clic en la tabla
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = jTable.rowAtPoint(e.getPoint());
                int column = jTable.columnAtPoint(e.getPoint());

                if (row > -1 && column == 4) {
                    int option = JOptionPane.showConfirmDialog(null, "Quieres Eliminar Este Producto. " +
                            tableModel.getValueAt(row, 1).toString() + "?",
                            "Cuidado Eliminar!X", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.OK_OPTION){
                        productRepository.delete((long) tableModel.getValueAt(row, 0));
                        tableModel.getRows().remove(row);
                        tableModel.fireTableDataChanged();
                    }
                    reset();
                } else if (row > -1 && column > -1) {
                    id = (long) tableModel.getValueAt(row, 0);
                    nameField.setText(tableModel.getValueAt(row, 1).toString());
                    priceField.setText(tableModel.getValueAt(row, 2).toString());
                    quantifyField.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        JScrollPane scroll = new JScrollPane(jTable);
        tablePanel.add(scroll);

        p.add(tablePanel, BorderLayout.SOUTH);
        p.add(formPanel, BorderLayout.NORTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Método para reiniciar el formulario
    private void reset() {
        id = 0;
        row = -1;
        nameField.setText("");
        priceField.setText("");
        quantifyField.setText("");
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        new JdbcSwingCrudProyect();
    }

    // Clase interna para el modelo de la tabla
    private class ProductTableModel extends AbstractTableModel {

        private String[] columns = new String[]{"Id", "Nombre", "Precio", "Cantidad", "Eliminar"};
        private List<Object[]> rows = new ArrayList<>();

        // Constructor del modelo de la tabla
        public ProductTableModel() {
            ProductRepository productRepository = new ProductRepositoryImp();
            List<Product> products = productRepository.findAll();
            for (Product product: products){
                Object[] row = {product.getId(), product.getName(), product.getPrice(), product.getQuantity(), "Eliminar"};
                rows.add(row);
            }
        }

        // Método para obtener las filas
        public List<Object[]> getRows() {
            return rows;
        }

        @Override
        public int getRowCount() {
            return this.rows.size();
        }

        @Override
        public int getColumnCount() {
            return this.columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return this.rows.get(rowIndex)[columnIndex];
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            this.rows.get(rowIndex)[columnIndex] = aValue;
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }
}