
public class Producto {
//	- variables de campo
	private String nombre;
	private int stock;
	
//	- constructor

	public Producto(String nombre, int stock) {
		this.nombre = nombre;
		this.stock = stock;
	}
	
//	- getter & setter

	public String getNombre() {
		return nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

//	- métodos
	
	public void aumentarStock(int cantidad) {
		stock += cantidad;
	}

	public void disminuirStock(int cantidad) {
		stock -= cantidad;
	}
}
