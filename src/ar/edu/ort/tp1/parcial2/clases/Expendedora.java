package ar.edu.ort.tp1.parcial2.clases;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import ort.tp1.tdas.implementaciones.ColaNodos;
import ort.tp1.tdas.interfaces.Cola;

public class Expendedora{

	private final static String MSJ_NOMBRE_INVALIDO = "Nombre Invalido";
	private final static String MSJ_ID_INVALIDO = "ID de producto Invalido";
	private static final String MSJ_ACTUALIZACION_NULA = "La actualizacion no puede ser nula";
	private static final String MSJ_PRODUCTO_NULO = "El producto no puede ser nulo";
	private static final String MSJ_PRODUCTO_REPETIDO = "ID de producto ya existente";
	private String nombre;
	private ListadoPorIdD ListaPorId;
	private ArrayList<String> erroresDeActualizacion;

	public Expendedora(String nombre) throws IllegalArgumentException {
		this.setNombre(nombre);
		this.ListaPorId = new ListadoPorIdD();
		this.erroresDeActualizacion = new ArrayList<>();

	}
	private void setNombre(String Nomb) {
		if((Nomb == null) || (Nomb.isEmpty()) || (Nomb.isBlank())) {
			throw new IllegalArgumentException(MSJ_NOMBRE_INVALIDO);
		}
		this.nombre = Nomb;
	}
	public void agregarProducto(Producto p) {
		// TODO chequeo de datos
		if(p == null) {
			throw new IllegalArgumentException(MSJ_PRODUCTO_NULO);
		}else if(p.getId() <1) {
			throw new IllegalArgumentException(MSJ_ID_INVALIDO);
		}else if(this.obtenerProductoPorId(p.getId())!= null){	
			throw new RuntimeException(MSJ_PRODUCTO_REPETIDO);
		}
		this.ListaPorId.add(p);
		
		
	}


	public void ajustarPrecio(int idProducto, Actualizacion actualizacion) {
		Producto producto = obtenerProductoPorId(idProducto);
		if (producto == null) {
			agregarError(MSJ_ID_INVALIDO, idProducto, actualizacion);
		} else if (actualizacion == null) {
			agregarError(MSJ_ACTUALIZACION_NULA, idProducto, actualizacion);
		} else {
			try {
				producto.aplicarActualizacion(actualizacion);
			} catch (RuntimeException re) {
				agregarError(re.getMessage(), idProducto, actualizacion);
			}
		}
	}
	
	private Producto obtenerProductoPorId(int idProducto) {
		Producto producto = null;
		producto = this.ListaPorId.search(idProducto);
		return producto;
	}

	private void agregarError(String mensaje, int idProducto, Actualizacion actualizacion) {
		erroresDeActualizacion.add(String.format("%s - %s - %s", mensaje, idProducto, actualizacion));
	}

	public void mostrarHistoricoActualizaciones() {
		// TODO Si te toco este tema NO
	}

	public void mostrarErrores() {
		// TODO Si te toco este tema
	}

	// Solo si te toco la lista por nombre
//	public void listarProductosPorNombre() {
//		productosPorNombre.listar();
//	}


	// Solo si te toco la lista por ID
	public void listarProductosPorId() {
	//	
		System.out.println("Se muestran los productos por id");
		this.ListaPorId.listar();
	}

}
