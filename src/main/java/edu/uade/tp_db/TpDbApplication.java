package edu.uade.tp_db;

import edu.uade.tp_db.entidades.Carrito;
import edu.uade.tp_db.entidades.Item;
import edu.uade.tp_db.entidades.Producto;
import edu.uade.tp_db.entidades.Usuario;

import edu.uade.tp_db.servicios.CarritoService;
import edu.uade.tp_db.servicios.ProductosService;
import edu.uade.tp_db.servicios.UsuariosService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class TpDbApplication implements CommandLineRunner {

	private static Usuario staticUsuario;
	private static UsuariosService staticUsuariosService;
	private static CarritoService staticCarritoService;
	private static ProductosService staticProductosService;

	private static Carrito carrito;
	private static List<Producto> productos;
	static Scanner scanner = new Scanner(System.in);

	@Autowired
	private UsuariosService usuariosService;
	@Autowired
	private CarritoService carritoService;
	@Autowired
	private ProductosService productosService;


	@PostConstruct
	public void init() {
		staticUsuariosService = this.usuariosService;
		staticCarritoService = this.carritoService;
		staticProductosService = this.productosService;
		productos = staticProductosService.verProductos();
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TpDbApplication.class);
		app.setHeadless(false);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		scanner = new Scanner(System.in);
		limpiarConsola();
		menu();
	}

	//------------------------MENUS------------------------
	public static void menu() {
		int opcion = 0;

		do {
			System.out.println("Ingresar opción deseada (1-3):");
			System.out.println("1. Registrarse");
			System.out.println("2. Iniciar sesión");
			System.out.println("3. Apagar sistema");

			while (!scanner.hasNextInt()) {
				System.out.println("Por favor ingresa un número válido.");
				scanner.next();
			}

			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					registrarse();
					break;
				case 2:
					iniciarSesion();
					break;
				case 3:
					scanner.close();
					System.out.println("Fin");
					return;
				default:
					System.out.println("Opción no válida, por favor ingresa un número entre 1 y 3.");
					break;
			}
		} while (opcion < 1 || opcion > 2);
	}

	public static void menuLogueado(){
		int opcion = 0;

		do {
			System.out.println("Ingresar opción deseada (1-4):");
			System.out.println("1. Manipular carrito");
			System.out.println("2. Ver mis pedidos");
			System.out.println("3. Ver catalogo de productos");
			System.out.println("4. Cerrar sesion");

			while (!scanner.hasNextInt()) {
				System.out.println("Por favor ingresa un número válido.");
				scanner.next();
			}

			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					menuCarrito();
					break;
				case 2:
					menupedidos();
					break;
				case 3:
					verProductos("menuLogueado");
					break;
				case 4:
					System.out.println("Cerrando sesion....");
					scanner.close();
					return;
				default:
					System.out.println("Opción no válida, por favor ingresa un número entre 1 y 5.");
					break;
			}
		} while (opcion < 1 || opcion > 5);
	}

	public static void menuCarrito(){
		int opcion = 0;

		do {
			System.out.println("Ingresar opción deseada (1-7):");
			System.out.println("1. Ver carrito");
			System.out.println("2. Agregar producto al carrito");
			System.out.println("3. Eliminar producto del carrito");
			System.out.println("4. Retroceder carrito");
			System.out.println("5. Ver catalogo");
			System.out.println("6. Realizar pedido");
			System.out.println("7. Volver al menu principal");

			while (!scanner.hasNextInt()) {
				System.out.println("Por favor ingresa un número válido.");
				scanner.next();
			}

			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					verCarrito();
					break;
				case 2:
					agregarProducto();
					break;
				case 3:
					eliminarProducto();
					break;
				case 4:
					retrocederCarrito();
					break;
				case 5:
					verProductos("menuCarrito");
					break;
				case 6:
					realizarPedido();
					break;
				case 7:
					menuLogueado();
					break;
				default:
					System.out.println("Opción no válida, por favor ingresa un número entre 1 y 7.");
					break;
			}
		} while (opcion < 1 || opcion > 7);
	}

	public static void menupedidos(){}

	public static void limpiarConsola(){
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}


	//------------------------LOGIN METODOS------------------------
	public static void iniciarSesion() {
		System.out.println("--------------------------------------------------");
		System.out.println("Formulario de Inicio de Sesión");
		System.out.println("--------------------------------------------------");
		System.out.println("Ingrese su mail:");
		String mailUsuario = scanner.nextLine();

		System.out.println("--------------------------------------------------");
		System.out.println("Ingrese su contraseña:");
		String contrasenia = scanner.nextLine();
		System.out.println("--------------------------------------------------");

		staticUsuario = staticUsuariosService.logearUsuario(mailUsuario, contrasenia);

		if (staticUsuario != null) {
			carrito = staticCarritoService.getCarritoPorUsuario(staticUsuario.getId());
			System.out.println("Inicio de sesión exitoso");
			menuLogueado();
		} else {
			System.out.println("Inicio de sesión fallido");
			System.out.println("--------------------------------------------------");
			menu();
		}
	}

	public static void registrarse() {
		System.out.println("--------------------------------------------------");
		System.out.println("Formulario de Registro");
		System.out.println("--------------------------------------------------");
		System.out.println("Ingrese su nombre:");
		String nombre = scanner.nextLine();

		System.out.println("--------------------------------------------------");
		System.out.println("Ingrese su apellido:");
		String apellido = scanner.nextLine();

		System.out.println("--------------------------------------------------");
		System.out.println("Ingrese su mail:");
		String mail = scanner.nextLine();

		System.out.println("--------------------------------------------------");
		System.out.println("Ingrese su contraseña:");
		String contrasenia = scanner.nextLine();

		System.out.println("--------------------------------------------------");
		System.out.println("Ingrese su dirección:");
		String direccion = scanner.nextLine();

		System.out.println("--------------------------------------------------");
		System.out.println("Ingrese su documento:");
		String documento = scanner.nextLine();
		System.out.println("--------------------------------------------------");

		if (staticUsuariosService.existeUsuario(mail)) {
			System.out.println("Ya existe un usuario con ese mail");
			menu();
		} else {

			staticUsuario = staticUsuariosService.agregarUsuario(new Usuario(nombre, apellido, mail, direccion, documento, contrasenia));
			carrito= new Carrito(staticUsuario.getId());
			System.out.println("Usuario registrado con éxito");
			System.out.println("--------------------------------------------------");
			menuLogueado();
		}
	}

	//------------------------CARRITO METODOS------------------------

	public static void verCarrito(){
		if (carrito.items.isEmpty()){
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("No hay productos en el carrito :C");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			menuCarrito();
		}
		else{
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			AtomicInteger acum = new AtomicInteger();
			carrito.getItems().forEach(item->{
				Producto producto = productos.stream()
						.filter(p -> p.getId().equals( item.getIdProducto())).findAny()
						.get();
				acum.addAndGet(producto.getPrecio() * item.getCantidad());
				System.out.println("--------------------------------------------------");
				System.out.println("Producto: " + producto.getNombre());
				System.out.println("Cantidad: " + item.getCantidad());
				System.out.println("Precio: " + producto.getPrecio());
				System.out.println("--------------------------------------------------");

			});
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Total actual: " + acum);
			menuCarrito();
		}
	}

	public static void eliminarProducto(){
		System.out.println("--------------------------------------------------");

		int idProducto = 0;
		int cantidad = 0;

		do {
			System.out.println("Ingrese el ID del producto que desea eliminar del carrito:");
			idProducto = scanner.nextInt();
			scanner.nextLine();

			if (idProducto > productos.size() || idProducto < 1) {
				System.out.println("ID inválido. Por favor, ingrese un ID de producto entre 1 y " + productos.size());
				continue;
			}

			System.out.println("--------------------------------------------------");
			System.out.println("Ingrese la cantidad que desea eliminar del carrito:");
			cantidad = scanner.nextInt();
			scanner.nextLine();

			int finalIdProducto = idProducto;
			int finalCantidad = cantidad;

			Producto producto = productos.stream()
					.filter(p -> p.getIdNormal()== finalIdProducto)
					.findFirst()
					.get();
			Item item = new Item(producto.getId(), finalCantidad);
			staticCarritoService.quitarProductoDeCarrito(item,carrito);

		} while (idProducto > productos.size() || idProducto < 1);

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		menuCarrito();
	}

	public static void realizarPedido(){}

	public static void agregarProducto(){
		System.out.println("--------------------------------------------------");

		int idProducto = 0;
		int cantidad = 0;

		do {
			System.out.println("Ingrese el ID del producto que desea agregar al carrito:");
			idProducto = scanner.nextInt();
			scanner.nextLine();

			if (idProducto > productos.size() || idProducto < 1) {
				System.out.println("ID inválido. Por favor, ingrese un ID de producto entre 1 y " + productos.size());
				continue;
			}

			System.out.println("--------------------------------------------------");
			System.out.println("Ingrese la cantidad que desea agregar al carrito:");
			cantidad = scanner.nextInt();
			scanner.nextLine();

			int finalIdProducto = idProducto;
			int finalCantidad = cantidad;

			Producto producto = productos.stream()
					.filter(p -> p.getIdNormal()== finalIdProducto)
					.findFirst()
					.get();
			Item item = new Item(producto.getId(), finalCantidad);
			staticCarritoService.agregarProductoACarrito(item,carrito);
			System.out.println("Producto " + producto.getNombre()  +" agregado al carrito.");

		} while (idProducto > productos.size() || idProducto < 1);

		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		menuCarrito();
	}

	public static void retrocederCarrito(){
		staticCarritoService.getAnteriorCarrito(carrito);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Carrito retrocedido");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		menuCarrito();
	}

	//------------------------PRODUCTOS METODOS------------------------

	public static void verProductos(String menuOrigen){
		System.out.println("--------------------------------------------------");
		System.out.println("Productos disponibles:");
		System.out.println("--------------------------------------------------");
		for (Producto producto : productos) {
			String nombre = producto.getNombre();
			int id = producto.getIdNormal();
			int precio = producto.getPrecio();
			int width = Math.max(nombre.length(), Integer.toString(precio).length()) + 10;
			String upperLine = "+" + "-".repeat(width) + "+";
			String nombreLine = String.format("| %-" + width + "s |", "Nombre: " + nombre);
			String precioLine = String.format("| %-" + width + "s |", "Precio: " + precio);
			String idLine = String.format("| %-" + width + "s |", "ID: " + id);
			System.out.println(upperLine);
			System.out.println(idLine);
			System.out.println(nombreLine);
			System.out.println(precioLine);
			System.out.println(upperLine);
			System.out.println();

		}
		if(menuOrigen.equals("menuLogueado")){
			menuLogueado();
		}
		else if(menuOrigen.equals("menuCarrito")){
			menuCarrito();
		}
	}

}
