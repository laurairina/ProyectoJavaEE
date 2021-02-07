package dao;

import java.util.List;

import javax.persistence.Query;
import org.springframework.stereotype.Component;
import model.Receta;

@Component 
public class RecetaDaoImpl  extends GenericDaoImpl<Receta, Integer>  implements RecetaDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Receta> obtenerPorNombreReceta(int usuarioid) {

		
		String jql = "from Receta r where r.usuario.idusu = :usuarioid";
		Query q = entityManager.createQuery(jql);
	
		q.setParameter("usuarioid",usuarioid);	
		return q.getResultList();

	}
		
	@SuppressWarnings("unchecked")
	public List<Receta> obtenerTodosReceta() {
	
		String jql = "from Receta r where r.categoria='Primero' order by r.fechapubli";
		Query q = entityManager.createQuery(jql);
	
		return q.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<Receta> obtenerTodosRecetaP() {
	
		String jql = "from Receta r where r.categoria='Postre' order by r.fechapubli";
		Query q = entityManager.createQuery(jql);
	
		return q.getResultList();

	}

	
}
