package dao;

import java.util.List;
import model.Receta;

public interface RecetaDao extends GenericDao<Receta, Integer> {	
	public List<Receta> obtenerPorNombreReceta(int id);
	public List<Receta> obtenerTodosReceta();
	
	public List<Receta> obtenerTodosRecetaP();
	
}
