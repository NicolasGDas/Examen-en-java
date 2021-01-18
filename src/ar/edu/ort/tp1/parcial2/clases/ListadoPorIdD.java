package ar.edu.ort.tp1.parcial2.clases;

import ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class ListadoPorIdD extends ListaOrdenadaNodos<Integer, Producto> implements Listable<Producto> {
	
	@Override
	public int compare(Producto dato1, Producto dato2) {
		return dato1.getId().compareTo(dato2.getId());
	}

	@Override
	public int compareByKey(Integer clave, Producto elemento) {
		return clave - elemento.getId();
	}

	@Override
	public void listar() {
		System.out.println("\nProductos por ID");
		for (Producto producto : this) {
			System.out.println(producto);
		}	
	}
	

}
