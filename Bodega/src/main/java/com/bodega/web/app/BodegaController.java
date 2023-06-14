package com.bodega.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BodegaController {
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/")
    public String mostrarInventario(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "inventario";
    }

    @PostMapping("/agregarProducto")
    public String agregarProducto(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/";
    }

    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/buscarProducto")
    public String buscarProducto(@RequestParam("nombre") String nombre, Model model) {
        Producto producto = productoRepository.findByNombre(nombre);
        if (producto != null) {
            model.addAttribute("producto", producto);
        } else {
            model.addAttribute("error", "Producto no encontrado");
        }
        return "detalle_producto";
    }

    @GetMapping("/mostrarProducto/{id}")
    public String mostrarProducto(@PathVariable("id") Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            model.addAttribute("producto", producto);
        } else {
            model.addAttribute("error", "Producto no encontrado");
        }
        return "detalle_producto";
    }

    @PostMapping("/aumentarStock/{id}")
    public String aumentarStock(@PathVariable("id") Long id, @RequestParam("cantidad") int cantidad) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.setStock(producto.getStock() + cantidad);
            productoRepository.save(producto);
        }
        return "redirect:/";
    }

    @PostMapping("/disminuirStock/{id}")
    public String disminuirStock(@PathVariable("id") Long id, @RequestParam("cantidad") int cantidad) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            int stockActual = producto.getStock();
            if (stockActual >= cantidad) {
                producto.setStock(stockActual - cantidad);
                productoRepository.save(producto);
            } else {
                // Manejar caso de stock insuficiente
            }
        }
        return "redirect:/";
    }
}
