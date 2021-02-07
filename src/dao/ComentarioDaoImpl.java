package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


import model.Comentario;


@Component 
public class ComentarioDaoImpl extends GenericDaoImpl<Comentario, Integer>  implements ComentarioDao{
	final static Logger milogger = Logger.getLogger(ComentarioDaoImpl .class);
	@SuppressWarnings("unchecked")
	@Override
	
	public List<Comentario> obtenerPorNombreComentario(int id) {
		
		List<Comentario> c=new ArrayList<Comentario>();
		try{
		   String jql ="from Comentario c where c.receta.idrect = :id_Receta"; // "from Comentario c where c.Receta.id = :idReceta";
		   Query q = entityManager.createQuery(jql);
	
        	
		  q.setParameter("id_Receta",id);
		 
		  c=q.getResultList();
		
		  return c;	
		}
	  catch(Exception e){
		 milogger.error("La conexion es nulo", e);
		 return null;
	  }
	}

	public Integer EliminarListaComentario(int id) {
		
		  String jql ="delete from Comentario c where c.receta.idrect = :id_Receta"; // "from Comentario c where c.Receta.id = :idReceta";
		Query q = entityManager.createQuery(jql);
	
      	
		q.setParameter("id_Receta",id);
		 
		return q.executeUpdate();

	}
	

	@Override
	public Comentario obtenerPorIdComentario(int idReceta) {
		
	 	String jql = "from Comentario r where r.receta.idrect = :id_Receta";
		Query q = entityManager.createQuery(jql);
	
		
	q.setParameter("id_Receta",idReceta);
		
		@SuppressWarnings("unchecked")
		List <Comentario> lista=q.getResultList();
		if(lista.isEmpty()){
			return null;
			
		}
		else{
			return lista.get(0);
		}	
	}

	public Integer EliminarComentario(int id) {	
		  String jql ="delete from Comentario c where c.idcoment = :id_comentario"; // "from Comentario c where c.Receta.id = :idReceta";
		Query q = entityManager.createQuery(jql);
	
		q.setParameter("id_comentario",id);	 
		return q.executeUpdate();
	}
	
	public Comentario idRecetaComentario(int id) {
		
	 	String jql = "from Comentario r where r.idcoment = :id_Coment";
			Query q = entityManager.createQuery(jql);	
		q.setParameter("id_Coment",id);
			
			@SuppressWarnings("unchecked")
			List <Comentario> lista=q.getResultList();
			if(lista.isEmpty()){
				return null;		
			}
			else{  return lista.get(0);  }
	}
}
