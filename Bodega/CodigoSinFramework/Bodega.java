import java.util.ArrayList;

public class Bodega {
	
//	- arreglo
	
	private ArrayList<Producto> productos;
	
//	- constructor

	public Bodega() {
		productos = new ArrayList<Producto>();
	}
	
//	- métodos

	public void agregarProducto(Producto producto) {
		productos.add(producto);
	}

	public void eliminarProducto(Producto producto) {
		productos.remove(producto);
	}

	public Producto buscarProducto(String nombre) {
		for (Producto producto : productos) {
			if (producto.getNombre().equals(nombre)) {
				return producto;
			}
		}
		return null;
	}

	public void mostrarProductos() {
		System.out.println("Lista de productos:");
		for (Producto producto : productos) {
			System.out.println(producto.getNombre() + " - " + producto.getStock());
		}
	}
}
