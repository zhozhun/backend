@SpringBootApplication
public class Main @SpringBootApplication{

	@Autowired
	private Bodega bodega;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Producto producto1 = new Producto("Medicamento1", 10);
		Producto producto2 = new Producto("Medicamento2", 20);
		Producto producto3 = new Producto("Medicamento3", 5);

		bodega.agregarProducto(producto1);
		bodega.agregarProducto(producto2);
		bodega.agregarProducto(producto3);

		bodega.mostrarProductos();

		Producto productoBuscado = bodega.buscarProducto("Medicamento1");
		if (productoBuscado != null) {
			System.out.println("Se encontró el producto " + productoBuscado.getNombre());
		} else {
			System.out.println("No se encontró el producto");
		}

		producto1.disminuirStock(3);
		bodega.mostrarProductos();
	}
}