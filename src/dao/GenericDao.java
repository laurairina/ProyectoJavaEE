package dao;

import java.util.List;

//Es una interfaz con genericos 
//para que luego se pueda implementar

public interface GenericDao<T, K> {
	public void agregar(T obj);
	public void modificar(T obj);
	public void eliminar(K id);
	public T obtener(K id);
	public List<T> obtenerTodos();
	
}
