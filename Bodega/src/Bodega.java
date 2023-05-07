import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Bodega {

    private List<Producto> productos;

    public Bodega() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void mostrarProductos() {
        System.out.println("Productos en bodega:");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " - Stock: " + producto.getStock());
        }
    }

    public Producto buscarProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }
}
