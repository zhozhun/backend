package com.bodega;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BodegaApplication extends JFrame {
    private List<Producto> productos;

    private JTextField nombreField;
    private JTextField stockField;
    private JTextField buscarField;
    private JTextField aumentarField;
    private JTextField disminuirField;
    private JTextArea resultadoArea;

    public BodegaApplication() {
        productos = new ArrayList<>();

        setTitle("Bodega Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de ingreso de producto
        JPanel ingresoPanel = new JPanel(new GridLayout(3, 2));
        ingresoPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        ingresoPanel.add(nombreField);
        ingresoPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        ingresoPanel.add(stockField);
        JButton agregarButton = new JButton("Agregar Producto");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        ingresoPanel.add(agregarButton);

        // Panel de operaciones
        JPanel operacionesPanel = new JPanel(new GridLayout(4, 2));
        operacionesPanel.add(new JLabel("Buscar Producto (Nombre):"));
        buscarField = new JTextField();
        operacionesPanel.add(buscarField);
        JButton buscarButton = new JButton("Buscar Producto");
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });
        operacionesPanel.add(buscarButton);
        operacionesPanel.add(new JLabel("Aumentar Stock (ID):"));
        aumentarField = new JTextField();
        operacionesPanel.add(aumentarField);
        JButton aumentarButton = new JButton("Aumentar Stock");
        aumentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarStock();
            }
        });
        operacionesPanel.add(aumentarButton);
        operacionesPanel.add(new JLabel("Disminuir Stock (ID):"));
        disminuirField = new JTextField();
        operacionesPanel.add(disminuirField);
        JButton disminuirButton = new JButton("Disminuir Stock");
        disminuirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disminuirStock();
            }
        });
        operacionesPanel.add(disminuirButton);

        // Área de resultados
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane resultadoScrollPane = new JScrollPane(resultadoArea);

        // Agregar los paneles al frame
        add(ingresoPanel, BorderLayout.NORTH);
        add(operacionesPanel, BorderLayout.CENTER);
        add(resultadoScrollPane, BorderLayout.SOUTH);
    }

    public void agregarProducto() {
        String nombre = nombreField.getText();
        String stockText = stockField.getText();
        int stock = Integer.parseInt(stockText);
        Producto producto = new Producto(nombre, stock);
        productos.add(producto);
        resultadoArea.append("Producto agregado: " + producto.getNombre() + "\n");
        nombreField.setText("");
        stockField.setText("");
    }

    public void buscarProducto() {
        String nombre = buscarField.getText();
        Producto producto = buscarProductoPorNombre(nombre);
        if (producto != null) {
            resultadoArea.append("Producto encontrado - Nombre: " + producto.getNombre() +
                    ", Stock: " + producto.getStock() + "\n");
        } else {
            resultadoArea.append("Producto no encontrado\n");
        }
        buscarField.setText("");
    }

    public void aumentarStock() {
        String idText = aumentarField.getText();
        Long id = Long.parseLong(idText);
        String cantidadText = JOptionPane.showInputDialog("Ingrese la cantidad para aumentar el stock:");
        int cantidad = Integer.parseInt(cantidadText);
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            producto.setStock(producto.getStock() + cantidad);
            resultadoArea.append("Stock aumentado para el producto " + producto.getNombre() +
                    ": " + producto.getStock() + "\n");
        } else {
            resultadoArea.append("El producto con ID " + id + " no existe\n");
        }
        aumentarField.setText("");
    }

    public void disminuirStock() {
        String idText = disminuirField.getText();
        Long id = Long.parseLong(idText);
        String cantidadText = JOptionPane.showInputDialog("Ingrese la cantidad para disminuir el stock:");
        int cantidad = Integer.parseInt(cantidadText);
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            int stockActual = producto.getStock();
            if (stockActual >= cantidad) {
                producto.setStock(stockActual - cantidad);
                resultadoArea.append("Stock disminuido para el producto " + producto.getNombre() +
                        ": " + producto.getStock() + "\n");
            } else {
                resultadoArea.append("Error: Stock insuficiente para el producto " + producto.getNombre() + "\n");
            }
        } else {
            resultadoArea.append("El producto con ID " + id + " no existe\n");
        }
        disminuirField.setText("");
    }

    private Producto buscarProductoPorId(Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    private Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BodegaApplication app = new BodegaApplication();
                app.setVisible(true);
            }
        });
    }
}

