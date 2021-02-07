package dao;

import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;


import model.Usuario;


@Component   //para que funcione esto es necesario tener en las dependencias spring
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer> implements UsuarioDao {

	public Usuario obtenerPorNombreUsuario(String usuario) {
		String jql = "from Usuario u where u.nombreusuario = :nomusuario";
		Query q = entityManager.createQuery(jql); 
	
		q.setParameter("nomusuario", usuario); //parametro que se va a pasar
		
		@SuppressWarnings("unchecked")
		List <Usuario> lista=q.getResultList();
		if(lista.isEmpty()){
			return null;	
		}
		else{
			return lista.get(0);
		}		
	}
	
	public boolean existeSesion(HttpSession session){
		
		if(session==null){
			return false;
		}
	   return true;
	}

	
	@Override
	public Usuario obtenerPorIdusuario(int id) {
		
	 	String jql = "from Usuario u where u.idusu = :idusu";
		Query q = entityManager.createQuery(jql);
		
	q.setParameter("idusu",id);
		
		@SuppressWarnings("unchecked")
		List <Usuario> lista=q.getResultList();
		if(lista.isEmpty()){
			return null;
			
		}
		else{
			return lista.get(0);
		}

	}
 	
	
	

}
