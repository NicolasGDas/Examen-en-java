package ar.edu.ort.tp1.parcial2.clases;

public interface Identificable<T> {
	T getId();
	boolean mismoId(T otroId);
}
