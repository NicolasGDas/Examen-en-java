package ar.edu.ort.tp1.parcial2.clases;


import ort.tp1.tdas.implementaciones.*;

public class Producto implements Apreciable, Identificable<Integer> {

	private static final String MSJ_AJUSTE_INVALIDO = "Actualizacion Invalido";
	private final static String MSJ_PRECIO_INVALIDO = "Precio Invalido";
	private final static String MSJ_NOMBRE_INVALIDO = "Nombre Invalido";
	private static final String MSJ_ID_INVALIDO = "ID Invalido";

	private Integer id;
	private String nombre;

	private PilaNodos<HistoricoActualizacion> historicoActualizaciones;

	public Producto(Integer id, double precioInicial, String nombre) {
		// TODO
		this.setId(id);
		this.setNombre(nombre);
		this.setHistoricoActualizaciones(precioInicial);
		
	}




	private void setHistoricoActualizaciones(double precioInicial) {
		// TODO Auto-generated method stub
		if(precioInicial < 0) {
			throw new IllegalArgumentException(MSJ_PRECIO_INVALIDO);
		}
		this.historicoActualizaciones = new PilaNodos<HistoricoActualizacion>();
		ActualizacionDirecta actIni = new ActualizacionDirecta(precioInicial);
		HistoricoActualizacion nuevoPrecio = new HistoricoActualizacion(actIni , 0, precioInicial);
		this.historicoActualizaciones.push(nuevoPrecio);
	}




	private void setNombre(String nombre) {
		//chequear que el nombre sea valido
		if((nombre == null) || (nombre.isEmpty()) || (nombre.isBlank()) ) {
			throw new IllegalArgumentException(MSJ_NOMBRE_INVALIDO);
		}
		this.nombre = nombre;
	}

	private void setId(int Id) {
		if(Id < 1) {
			throw new IllegalArgumentException(MSJ_ID_INVALIDO);
		}
		this.id = Id;
	}


	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public boolean mismoId(Integer otroId) {
		return otroId.equals(id);
	}
	
	public String getNombre() {
		return nombre;
	}

	public double obtenerPrecio() {
		double ultimoPrecio = 0;
		if (!historicoActualizaciones.isEmpty()) {
			ultimoPrecio = historicoActualizaciones.peek().getPrecio();
		}
		return ultimoPrecio;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", precio=" + obtenerPrecio() + ", nombre=" + nombre + "]";
	}

	@Override
	public void aplicarActualizacion(Actualizacion actualizacion) throws RuntimeException {
		if (actualizacion == null) {
			throw new RuntimeException(MSJ_AJUSTE_INVALIDO);
		}
		double precio = obtenerPrecio();
		double nuevoPrecio =0;
		if(actualizacion instanceof ActualizacionDirecta) {
			nuevoPrecio = ((ActualizacionDirecta)actualizacion).obtenerValorCalculado();
		}else {
			nuevoPrecio = ((ActualizacionExtendida)actualizacion).obtenerValorCalculado(precio);
		}
		if (nuevoPrecio < 0) {
			throw new RuntimeException(MSJ_PRECIO_INVALIDO);
		}
		this.historicoActualizaciones.push(new HistoricoActualizacion(actualizacion, precio, nuevoPrecio));
	}

	public void listarActualizacionesDePrecio() {
		// TODO
		//al ser una pila tengo que usar una pila aux
		
		PilaNodos<HistoricoActualizacion> aux = new PilaNodos<HistoricoActualizacion>();
		HistoricoActualizacion productoAMostrar;
		//tengo que sacar un item de mi pila, mostrarlo y guardarlo en mi aux
		while(!this.historicoActualizaciones.isEmpty()) {
			productoAMostrar = this.historicoActualizaciones.pop();
			System.out.println(productoAMostrar);
			aux.push(productoAMostrar);
		}
		//ahora vuelvo a guardar en mi pila del producto
		
		while(!aux.isEmpty()) {
			this.historicoActualizaciones.push(aux.pop());
		}
		
	}
}